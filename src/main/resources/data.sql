-- Insert mock Candidate

INSERT INTO candidate(first_name, last_name, phone, email) SELECT 'Gabi', 'Trocan', '0760821214','gabrieltrocan93@gmail.com'
WHERE NOT EXISTS( SELECT email FROM candidate WHERE email = 'gabrieltrocan93@gmail.com');