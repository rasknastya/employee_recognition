create table users (
  username VARCHAR(256) PRIMARY KEY,
  password VARCHAR(256),
  enabled boolean
);

create table user_roles (
  username  VARCHAR(256) REFERENCES users(username) ON DELETE CASCADE,
  role VARCHAR(256),
  PRIMARY KEY (username, role)
);
