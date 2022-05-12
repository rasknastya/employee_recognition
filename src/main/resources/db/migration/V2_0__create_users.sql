create table IF NOT EXISTS users (
    user_id text PRIMARY KEY,
    email text,
    full_name text,
    embedding decimal[],
    password text,
    enabled boolean
);