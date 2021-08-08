Insert into companies (name) select 'company1';

Insert into branches (branch_name, company_id) select 'test_branch', 1;

insert into roles (name, status) VALUES 
    ('Администратор', 'ADMIN'),
    ('Студент', 'STUDENT'),
    ('Преподаватель', 'PRESENTER');

insert into usrs (email, full_name, login, role_id) VALUES
    ('example@post.org',	'Peter Parker', 'spiderman', 3),
    ('phil@post.ru',	'Phil Jones', 'defender', 3),
    ('frankie@chelsea.com',	'Frank Lampard', 'lamps', 1),
    ('stieve@apple.org',	'Stieve Jobs', 'mactop', 2);

insert into rooms (number)
VALUES (54), (27), (228);

insert into presentations (description, room_id, subject, title, lasts, date) VALUES
    ('лекция', 1, 'math', 'заголовокMath', 1.30, '09.08.2021'),
    ('лекция', 2, 'history', 'заголовокHistory', 2.00, '11.08.2021'),
    ('лекция', 3, 'bio', 'заголовокBio', 1.45, '10.08.2021');

insert into presentation_users(presentation_id, user_id) VALUES
    (1, 1), (2, 2), (3, 1);


