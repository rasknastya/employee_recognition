CREATE TABLE if not exists request (
    request_id string PRIMARY KEY,
    request_time timestamp,
    user_id  string,
    mark_id  string,
    changed_mark_id string,
    commentary string
  );

