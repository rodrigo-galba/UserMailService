-- login: admin
-- password: s3cr3t
INSERT INTO USERS (ID, ADMIN, EMAIL, LOGIN, NAME, PASSWORD, CREATED_DATE, UPDATED_DATE) VALUES (nextval('public."hibernate_sequence"'), TRUE, 'admin@email.com', 'admin', 'Admin user', '$2a$10$f/26FSdLtHbuRhPk5bpwJeMZgykz0wPGzGerhyEh6GmfQEzT8JCFO', NOW(), NOW());