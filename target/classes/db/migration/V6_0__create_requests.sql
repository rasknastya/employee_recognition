CREATE TABLE if not exists request (
    request_id text PRIMARY KEY,
    request_time timestamp,
    user_id  text,
    mark_id  text,
    changed_mark_id text,
    commentary text
  );

