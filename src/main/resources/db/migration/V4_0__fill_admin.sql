INSERT INTO users(user_id, email, full_name, embedding, password, enabled)
SELECT * FROM (SELECT '0884831b-42f9-4f81-af88-3e777be69c8f', 'rasknastya@gmail.com', 'Анастасия Сергеевна Крупина', null, '$2a$12$MoBE4eBLNBrM7G7bLg8We.AvVLINSh1YOS.YXPp3EEwYq/6eOTeD6', True) AS tmp
WHERE NOT EXISTS (
        SELECT userId FROM users WHERE userId = '0884831b-42f9-4f81-af88-3e777be69c8f'
    ) LIMIT 1;

INSERT INTO user_roles(user_id, role)
SELECT * FROM (SELECT '0884831b-42f9-4f81-af88-3e777be69c8f', 'ADMIN') AS tmp
WHERE NOT EXISTS (
        SELECT userId, role FROM user_roles AS u WHERE u.userId = '0884831b-42f9-4f81-af88-3e777be69c8f' AND u.role = 'ADMIN'
    ) LIMIT 1;