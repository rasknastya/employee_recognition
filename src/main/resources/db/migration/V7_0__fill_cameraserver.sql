INSERT INTO users(user_id, email, full_name, embedding, password, enabled)
SELECT * FROM (SELECT '0884831b-42f9-4f81-af88-3e777be69c8c', 'cameramodule', '', ARRAY [0], '$2a$12$BVJ2nAgJEI3LSY5Ue/7pLu9RHQvD5oSzB9RWC/jRA3pytocIiW356', True) AS tmp
WHERE NOT EXISTS (
        SELECT user_id FROM users WHERE user_id = '0884831b-42f9-4f81-af88-3e777be69c8c'
    ) LIMIT 1;

INSERT INTO user_roles(user_id, role)
SELECT * FROM (SELECT '0884831b-42f9-4f81-af88-3e777be69c8c', 'CAMERAMODULE') AS tmp
WHERE NOT EXISTS (
        SELECT user_id, role FROM user_roles AS u WHERE u.user_id = '0884831b-42f9-4f81-af88-3e777be69c8c' AND u.role = 'CAMERAMODULE'
    ) LIMIT 1;
select * from users;