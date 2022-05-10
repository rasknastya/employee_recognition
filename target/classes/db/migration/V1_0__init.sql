CREATE TABLE books (
  id bigserial PRIMARY KEY,
  book_name varchar,
  author varchar);

INSERT INTO books (book_name, author) VALUES
('Thinking in Java', 'Bruce Eckel'),
('Clean Code', 'Robert C. Martin'),
('Spring in Action', 'Craig Walls ');
