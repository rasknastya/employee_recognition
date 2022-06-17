from facenet_pytorch import MTCNN, InceptionResnetV1
import torch
from torch import nn
from PIL import Image
import numpy as np


def mean(embeddings):
    return torch.mean(embeddings, 0)


def cos(a, b):
    norm_dot_product = sum(a * b) / (torch.linalg.norm(a) * torch.linalg.norm(b))
    return norm_dot_product


class FaceIdentifierModel(nn.Module):
    def forward(self, images):
        aligned = []
        face_images = []
        for i in range(len(images)):
            image = images[i]
            bounding_boxes = self.mtcnn.detect(image)[0]
            if bounding_boxes is None:
                continue
            for j in range(bounding_boxes.shape[0]):
                bounding_box = bounding_boxes[j].astype(int)
                face = image[bounding_box[1]:bounding_box[3], bounding_box[0]:bounding_box[2]]
                if face.shape[0] + face.shape[1] < 80:
                    continue
                face_images.append(face)
                face = Image.fromarray(face).resize((160, 160))
                face = np.asarray(face)
                face = face / 255  # normalize
                face = np.moveaxis(face, 2, 0)
                face = torch.from_numpy(face).float()
                aligned.append(face)
        if len(aligned) == 0:
            return None
        aligned = torch.stack(aligned).to(self.device)
        embeddings = self.facenet(aligned).detach().cpu()
        return face_images, embeddings

    def __init__(self):
        super(FaceIdentifierModel, self).__init__()
        self.device = torch.device('cuda:0' if torch.cuda.is_available() else 'cpu')
        self.mtcnn = MTCNN()
        self.facenet = InceptionResnetV1(pretrained='vggface2').eval().to(self.device)
