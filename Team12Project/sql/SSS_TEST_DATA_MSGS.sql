use supershoppingsurfer_silver;
insert into message(content, subject, timestamp) values('This is a message from admin to all users.', 'Test',CURRENT_TIMESTAMP);
insert into message(content, subject, timestamp) values('This is a message.', 'Test from ole',CURRENT_TIMESTAMP);
insert into message(content, subject, timestamp) values('This is a message.', 'Test from gjermund',CURRENT_TIMESTAMP);
insert into message(content, subject, timestamp) values('This is a message.', 'Test from roger',CURRENT_TIMESTAMP);
insert into message(content, subject, timestamp) values('This is a message.', 'Test from andreas',CURRENT_TIMESTAMP);
insert into message(content, subject, timestamp) values('This is a message.', 'Test from hallgeir',CURRENT_TIMESTAMP);

INSERT INTO  messageSender(message_id, username)
values	(1, 'admin'), (2, 'ole'), (3, 'gjermund'), (4, 'roger'), (5, 'andreas'), (6, 'hallgeir');

INSERT INTO messageReciever(message_id, username)
VALUES	(1, 'ole'), (1, 'gjermund'), (1, 'roger'), (1, 'andreas'), (1, 'hallgeir'),
		(2, 'admin'), (3, 'admin'), (4, 'admin'), (5, 'admin'), (6, 'admin');
