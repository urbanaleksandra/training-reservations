insert into user (username, password, first_name, last_name, email, enabled, deleted) values
	('admin', '$2y$10$ddb311ZFwLrO7ySNVYtXkuPosS/.hj9AacZef4FyxRI6l70.jqk3.', 'Aleksandra', 'Urban', 'urb.saska@gmail.com', true, false),
    ('GYM-pera97', '$2y$10$ddb311ZFwLrO7ySNVYtXkuPosS/.hj9AacZef4FyxRI6l70.jqk3.', 'Pera', 'Peric', 'urb.saska@gmail.com', true, false),
    ('GYM-mika97', '$2y$10$ddb311ZFwLrO7ySNVYtXkuPosS/.hj9AacZef4FyxRI6l70.jqk3.', 'Mika', 'Mikic', 'urb.saska@gmail.com', true, false),
    ('somi97', '$2y$10$ddb311ZFwLrO7ySNVYtXkuPosS/.hj9AacZef4FyxRI6l70.jqk3.', 'Mihailo', 'Stanarevic', 'urb.saska@gmail.com', true, false),
    ('kaja97', '$2y$10$ddb311ZFwLrO7ySNVYtXkuPosS/.hj9AacZef4FyxRI6l70.jqk3.', 'Katarina', 'Urban', 'urb.saska@gmail.com', true, false),
    ('maja91', '$2y$10$ddb311ZFwLrO7ySNVYtXkuPosS/.hj9AacZef4FyxRI6l70.jqk3.', 'Maja', 'Urban', 'urb.saska@gmail.com', true, false),
	('pera97', '$2y$10$ddb311ZFwLrO7ySNVYtXkuPosS/.hj9AacZef4FyxRI6l70.jqk3.', 'Pera', 'Peric', 'urb.saska@gmail.com', true, false);
insert into admin (id, user_id, deleted) values (1, 1, false);

insert into employee (id, user_id, deleted) values
	(1, 2, false),
    (2, 3, false);

insert into training_package (id, package_type, monthly_price, deleted) values
    (1, 0, 5000, false),
    (2, 1, 2000, false),
    (3, 2, 4000, false);

insert into simple_user (id, user_id, card_number, date_of_last_payment, violations, training_package_id, deleted) values
	(1,4, '923456', '2020-07-26', 0, 1, false),
	(2,5, '235686', '2020-07-05', 0, 2, false),
	(3,6, '158585', '2020-07-20', 0, 3, false),
    (4,7, '333456', '2020-07-18', 0, 2, false);

insert into roles (name, deleted) values ('ROLE_ADMIN', false),
    ('ROLE_EMPLOYEE', false),
    ('ROLE_SIMPLE_USER', false);

insert into user_roles (user_id, role_id) values
    ( 1, 1),
    ( 2, 2),
    ( 3, 2),
    ( 4, 3),
    ( 5, 3),
    ( 6, 3),
    ( 7, 3);

insert into permission (name, deleted) values ('user', false);
insert into permission (name, deleted) values ('admin', false);
insert into permission (name, deleted) values ('simple', false);
insert into permission (name, deleted) values ('employee', false);

insert into role_permissions (role_id, permission_id) values (1, 1);
insert into role_permissions (role_id, permission_id) values (2, 1);
insert into role_permissions (role_id, permission_id) values (3, 1);
insert into role_permissions (role_id, permission_id) values (1, 2);
insert into role_permissions (role_id, permission_id) values (2, 4);
insert into role_permissions (role_id, permission_id) values (3, 3);

insert into training (id, name, description, capacity, duration, deleted) values
    (1, 'Functional', 'Training for endurance of all body muscles', 20, 1, false),
    (2, 'Insanity', 'Full cardio training for the whole body', 15, 2, false),
    (3, 'Booty Pump', 'Usually a training for women. Works on abs, legs and booty.', 25, 1, false);

insert into days (id, day, deleted) values
    (1, 'MONDAY', false),
    (2, 'TUESDAY', false),
    (3, 'WEDNESDAY', false),
    (4, 'THURSDAY', false),
    (5, 'FRIDAY', false),
    (6, 'SATURDAY', false);

insert into training_day (id, day_id, training_id, trainer, starts_at, deleted) values
    (1, 1, 1, 'Marko Filipovic', '08:00', false),
    (2, 2, 1, 'Marko Filipovic', '10:00', false),
    (3, 3, 1, 'Marko Filipovic', '08:00', false),
    (4, 4, 1, 'Marko Filipovic', '09:00', false),
    (5, 5, 1, 'Marko Filipovic', '08:00', false),
    (6, 6, 1, 'Marko Filipovic', '10:00', false),
    (7, 1, 1, 'Nenad Stamenkovic', '10:00', false),
    (8, 2, 1, 'Nenad Stamenkovic', '12:00', false),
    (9, 3, 1, 'Nenad Stamenkovic', '12:00', false),
    (10, 4, 1, 'Nenad Stamenkovic', '11:00', false),
    (11, 5, 1, 'Nenad Stamenkovic', '10:00', false),
    (12, 6, 1, 'Nenad Stamenkovic', '12:00', false),
    (13, 1, 2, 'Andjela Pavkov', '17:00', false),
    (14, 3, 2, 'Andjela Pavkov', '17:00', false),
    (15, 5, 2, 'Andjela Pavkov', '17:00', false),
    (16, 1, 2, 'Andjela Pavkov', '20:00', false),
    (17, 3, 2, 'Andjela Pavkov', '21:00', false),
    (18, 5, 2, 'Andjela Pavkov', '20:00', false),
    (19, 2, 3, 'Stefan Pavic', '17:00', false),
    (20, 4, 3, 'Stefan Pavic', '17:00', false),
    (21, 6, 3, 'Stefan Pavic', '18:00', false),
    (22, 2, 3, 'Stefan Pavic', '20:00', false),
    (23, 4, 3, 'Stefan Pavic', '20:00', false),
    (24, 6, 3, 'Stefan Pavic', '20:00', false);
