CREATE TABLE if not exists requests (
    request_id text PRIMARY KEY,
    request_time timestamp,
    user_id  text references users(user_id) on delete cascade,
    mark_id  text,
    changed_mark_id text,
    commentary text
  );