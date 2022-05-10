CREATE TABLE if not exists marks (
    mark_id string PRIMARY KEY,
    mark_time timestamp,
    frame_address string,
    user_id string,
    confidence float,
    approved boolean
  );

