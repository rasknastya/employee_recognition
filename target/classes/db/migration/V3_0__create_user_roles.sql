create table IF NOT EXISTS user_roles (
                            user_id  text REFERENCES users(user_id) ON DELETE CASCADE,
                            role text,
                            PRIMARY KEY (user_id, role)
);