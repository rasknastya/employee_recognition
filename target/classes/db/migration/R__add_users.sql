delete from users;
delete from user_roles;

insert into users (username, password, enabled)
    values ('admin', '$2a$12$piyxXSAH7mHpE5LsXOc60uatlJHevUytN.PcMcS7DcSmmabvvOY32', true);
insert into user_roles (username, role)
    values ('admin', 'USER');
insert into user_roles (username, role)
    values ('admin', 'ADMIN');

insert into users (username, password, enabled)
    values ('user', '$2a$12$9aXvwSBl8DocZo.A44z37.8JYohRV0dENenxW3sr3gkN6e6T8wAm.', true);
insert into user_roles (username, role)
    values ('user', 'USER');

