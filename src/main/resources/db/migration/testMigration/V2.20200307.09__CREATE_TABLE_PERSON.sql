CREATE TABLE person(
    id int AUTO_INCREMENT primary key,
    first_name VARCHAR(100),
    middle_name VARCHAR(100),
    last_name VARCHAR(100),
    age INT,
    gender_id int not null,
    address VARCHAR(100)
);


