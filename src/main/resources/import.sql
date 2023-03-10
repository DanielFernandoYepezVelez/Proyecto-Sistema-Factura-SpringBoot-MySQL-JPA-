              /* REGIONS */
INSERT INTO `regions` (name) VALUES ('Sudamérica');
INSERT INTO `regions` (name) VALUES ('Centroamérica');
INSERT INTO `regions` (name) VALUES ('Norteamérica');
INSERT INTO `regions` (name) VALUES ('Europa');
INSERT INTO `regions` (name) VALUES ('Asia');
INSERT INTO `regions` (name) VALUES ('Africa');
INSERT INTO `regions` (name) VALUES ('Oceanía');
INSERT INTO `regions` (name) VALUES ('Antártida');

                                                                   /* CLIENTS */
INSERT INTO `clients` (name, last_name, email, created_at, photo, region_id) VALUES ('Daniel', 'Yepez', 'danipez@gmail.com', '2017-08-11', '', 1);
INSERT INTO `clients` (name, last_name, email, created_at, photo, region_id) VALUES ('Fernando', 'Velez', 'ferpez@gmail.com', '2016-02-29', '', 2);
INSERT INTO `clients` (name, last_name, email, created_at, photo, region_id) VALUES ('Harvy', 'Garcia', 'harvy@gmail.com', '2019-03-23', '', 3);
INSERT INTO `clients` (name, last_name, email, created_at, photo, region_id) VALUES ('Camilo', 'Guzman', 'camilo@gmail.com', '2020-02-17', '', 4);
INSERT INTO `clients` (name, last_name, email, created_at, photo, region_id) VALUES ('Esteban', 'Rodriguez', 'esteban@gmail.com', '2021-01-18', '', 5);
INSERT INTO `clients` (name, last_name, email, created_at, photo, region_id) VALUES ('Manuel', 'Tedeoro', 'manuel@gmail.com', '2022-06-19', '', 6);
INSERT INTO `clients` (name, last_name, email, created_at, photo, region_id) VALUES ('Dona', 'Bustamante', 'dona@gmail.com', '2023-11-20', '', 7);
INSERT INTO `clients` (name, last_name, email, created_at, photo, region_id) VALUES ('Camila', 'Restrepo', 'restrecamila@gmail.com', '2021-09-21', '', 8);
INSERT INTO `clients` (name, last_name, email, created_at, photo, region_id) VALUES ('Mariela', 'Lopez', 'mariela@gmail.com', '2021-06-22', '', 1);
INSERT INTO `clients` (name, last_name, email, created_at, photo, region_id) VALUES ('Candy', 'Arizona', 'candy@gmail.com', '2023-01-19', '', 2);

/* Creamos Algunos Usuarios Con Sus Respectivos Roles */
INSERT INTO `users` (name, last_name, username, password, email, enabled) VALUES ('Daniel Fernando', 'Yepez Velez', 'daniel', '$2a$10$mpz5cLJgSkk4fl.kQVNGuO9yi5bHs5a.1dZPpLKMT6E7eKagZKv0C', 'danipez.02@gmail.com', 1);
INSERT INTO `users` (name, last_name, username, password, email, enabled) VALUES ('Maria Helena', 'Gomez Rojas', 'admin', '$2a$10$PaVuVfONfXxFLZ3Ld.jVRe.6PbdsEZZ5/3Rcqw/7Me8Kpp/68uoXq', 'mariah.02@gmail.com', 1);

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id, role_id) VALUES (1, 1);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 2);
INSERT INTO `users_roles` (user_id, role_id) VALUES (2, 1);