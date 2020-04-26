# drop table candidate;
# drop table interviewer;
# drop table interview;

SET GLOBAL default_storage_engine = 'InnoDB';


-- Insert mock Candidate
INSERT INTO candidate(first_name, last_name, phone, email) SELECT 'Gabi', 'Trocan', '0760821214','gabriel.trocan@my.fmi.unibuc.ro'
WHERE NOT EXISTS( SELECT email FROM candidate WHERE email = 'gabriel.trocan@my.fmi.unibuc.ro');
INSERT INTO candidate(first_name, last_name, phone, email) SELECT 'Alexandra', 'Ivan', '0761831264','alexandraivan97@gmail.com'
WHERE NOT EXISTS( SELECT email FROM candidate WHERE email = 'alexandraivan97@gmail.com');


-- Insert mock Interviewer
INSERT INTO interviewer(first_name, last_name, email, department) SELECT 'Gabi', 'Trocan','gabrieltrocan93@gmail.com', 'it'
WHERE NOT EXISTS( SELECT email FROM candidate WHERE email = 'gabrieltrocan93@gmail.com');


-- Insert mock Interview
INSERT INTO interview(date, reserved_room, score, type, candidate_id, interviewer_id) VALUES
('2020.05.01', 'Bill Gates Room', 1, 'HR Interview', 1, 1);
INSERT INTO interview(date, reserved_room, score, type, candidate_id, interviewer_id) VALUES
('2020.05.02', 'Delta Dunarii Room', 1, 'Technical Interview', 1, 1);
INSERT INTO interview(date, reserved_room, score, type, candidate_id, interviewer_id) VALUES
('2020.05.11', 'Bill Gates Room', 1, 'HR Interview', 2, 1);