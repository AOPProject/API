# drop table candidate;
# drop table interviewer;
# drop table interview;

SET GLOBAL default_storage_engine = 'InnoDB';


-- Insert mock Candidate

INSERT INTO candidate(first_name, last_name, phone, email) SELECT 'Gabi', 'Trocan', '0760821214','gabriel.trocan@my.fmi.unibuc.ro'
WHERE NOT EXISTS( SELECT email FROM candidate WHERE email = 'gabriel.trocan@my.fmi.unibuc.ro');


-- Insert mock Interviewer
INSERT INTO interviewer(first_name, last_name, email, department) SELECT 'Gabi', 'Trocan','gabrieltrocan93@gmail.com', 'it'
WHERE NOT EXISTS( SELECT email FROM candidate WHERE email = 'gabrieltrocan93@gmail.com');