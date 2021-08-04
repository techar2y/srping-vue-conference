Insert into company (company_name) select 'company1';

Insert into branch (branch_name, company_id) select 'test_branch', 1;

insert into roles (name, status) select 'Администратор', 'ADMIN';
insert into roles (name, status) select 'Студент', 'STUDENT';
insert into roles (name, status) select 'Преподаватель', 'PRESENTER';

insert into users (email, full_name, login, role_id) select 'example@post.org',	'Peter Parker', 'spiderman', 3;

insert into rooms (name, number) select 'Аудитория', 54;

insert into presentations (date, description, room_id, subject, title) select '05.06.2021', 'лекция', 1, 'math', 'заголовк';


