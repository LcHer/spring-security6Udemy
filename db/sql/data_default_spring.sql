-----------data-------------

insert into users (username, password, enabled) VALUES
                                                    ('admin', 'to_be_encoded', true),
                                                    ('user', 'to_be_encoded', true);

insert into authorities (username, authority) VALUES
                                                  ('admin', 'admin'),
                                                  ('user', 'user');




--insert into roles(role_name, description, id_customer) VALUES  esta forma de datos es para hacer uso de las autorities
--        ('VIEW_ACCOUNT', 'can view account endpoint', 1),
--        ('VIEW_CARDS', 'can view cards endpoint', 2),
--      ('VIEW_LOANS', 'can view loans endpoint', 3),
--     ('VIEW_BALANCE', 'can view balance endpoint', 4);                                               ('user@email.com', '{noop}to_be_encoded', 'user');

