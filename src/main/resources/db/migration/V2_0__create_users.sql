create table IF NOT EXISTS users (
    user_id string PRIMARY KEY,
    email string,
    full_name string,
    embedding float[],
    password string,
    enabled boolean
);