CREATE TABLE if not exists marks (
    mark_id text PRIMARY KEY,
    mark_time timestamp,
    frame_address text,
    user_id text,
    confidence float,
    approved boolean
  );
select * from marks;