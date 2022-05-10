create table IF NOT EXISTS user_roles (
                            user_id  string REFERENCES users(user_id) ON DELETE CASCADE,
                            role string,
                            PRIMARY KEY (user_id, role)
);