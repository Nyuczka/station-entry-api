INSERT INTO user_role(name)
values ('user'),
       ('admin');

INSERT INTO users(username, password, role_name)
values ('user', 'password', 'user'),
       ('admin', 'nimda', 'admin');