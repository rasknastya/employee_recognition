import json
from datetime import datetime
from io import BytesIO

from pydantic.fields import List

from FaceRecognitionModel import *
import cv2
from fastapi import FastAPI, UploadFile, File, Form, Request
from PIL import Image
import numpy as np
from starlette.middleware.cors import CORSMiddleware
import requests

token = ''
found = []
last_found_len = 0
app = FastAPI()
model = FaceIdentifierModel()
origins = ["*"]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=origins,
    allow_headers=origins,
)


def get_token():
    global token
    url = 'http://127.0.0.1:8080/login'
    head = {'Content-Type': 'application/json'}
    data = {'email': 'cameramodule', 'password': 'camerapass'}
    response = requests.post(url, data=json.dumps(data), headers=head)
    token = response.json().get('token')


def load_image(data):
    return Image.open(BytesIO(data))


def get_embeddings():
    global token
    head = {'Authorization': 'Bearer ' + token}
    url = 'http://127.0.0.1:8080/users/embeddings'
    return requests.get(url, headers=head)


def send_mark(body):
    head = {'Authorization': 'Bearer ' + token}
    url = 'http://127.0.0.1:8080/camera'
    return requests.post(url, data=body, headers=head)


@app.post("/capture")
async def root(files: List[UploadFile] = File(...)):
    global found
    global last_found_len
    images = []
    for file in files:
        image = load_image(await file.read())
        image = cv2.cvtColor(np.asarray(image), cv2.COLOR_RGB2BGR)
        images.append(image)
    res = model.forward(images)
    if res is None:
        return {"status": "Nobody is found"}
    found_embeddings = res[1]
    found_photos = res[0]

    response = get_embeddings()
    if response.status_code != 200:
        get_token()
        response = get_embeddings()
        if response.status_code != 200:
            return None

    map_embeddings = response.json()

    ids = list(filter(lambda x: len(map_embeddings.get(x)) == 512, map_embeddings.keys()))
    embeddings = list(filter(lambda x: len(x) == 512, map_embeddings.values()))

    embeddings = torch.FloatTensor(embeddings)
    dot_prod = torch.matmul(embeddings, found_embeddings.T)
    norm = np.dot(torch.linalg.norm(embeddings, dim=1).reshape(-1, 1),
                  (torch.linalg.norm(found_embeddings, dim=1)).reshape(1, -1))
    cosines = abs(dot_prod / norm)
    cosines[cosines < 0.6] = 0

    current_found_len = 0
    for i in range(cosines.shape[1]):
        column = cosines[:, i]
        max_idx = np.argmax(np.array(column))
        max_prob = column[max_idx]
        if max_prob > 0:
            person_id = ids[max_idx]
            if person_id in found:
                continue
            now = datetime.now()
            path = '/Users/rasknastya/Documents/employees/' + now.strftime("%Y-%m-%d %H:%M:%S:%f") + '.jpg'
            cv2.imwrite(path, found_photos[i])
            body = {"frameAddress": path, "userId": person_id,
                    "confidence": max_prob.item(), "markTime": now}
            found.append(person_id)
            current_found_len += 1
            send_mark(body)

    found = found[last_found_len:]
    last_found_len = current_found_len


@app.post("/getsmaller")
async def root(files: List[UploadFile] = File(...)):
    i = 0
    for image in files:
        image = load_image(await image.read())
        image = cv2.cvtColor(np.asarray(image), cv2.COLOR_RGB2BGR)
        image = cv2.resize(image, (1280, 720))
        cv2.imwrite(str(i) + ".jpg", image)
        i += 1


@app.post("/getembedding")
async def get_embedding(files: List[str] = Form(...)):
    images = []
    for file in files:
        file = file.encode("ISO 8859-1")
        file = BytesIO(file)
        file = Image.open(file)
        image = cv2.cvtColor(np.asarray(file), cv2.COLOR_RGB2BGR)
        images.append(image)
        print(file)

    res = model.forward(images)
    now = datetime.now()

    if res is None:
        return None

    found_embeddings = res[1]
    found_photos = res[0]
    person_vector = mean(found_embeddings)
    person_vector, found_embeddings, found_photos = clean_embeds(person_vector, found_embeddings, found_photos)

    for i in range(len(found_photos)):
        photo = found_photos[i]
        path = '/Users/rasknastya/Documents/employees/registered/' + now.strftime("%Y-%m-%d %H:%M:%S:%f") \
               + ' ' + str(i) + '.jpg'
        cv2.imwrite(path, photo)
    return person_vector.tolist()


def clean_embeds(mean_embed, embeds, photos):
    for i in range(len(embeds)):
        embed = embeds[i]
        cosine = cos(mean_embed, embed)
        if cosine < 0.6:
            embeds = torch.cat([embeds[0:i], embeds[i + 1:]])
            photos.pop(i)
            mean_embed = mean(embeds)
            mean_embed, embeds, photos = clean_embeds(mean_embed, embeds, photos)
            break
    return mean_embed, embeds, photos
