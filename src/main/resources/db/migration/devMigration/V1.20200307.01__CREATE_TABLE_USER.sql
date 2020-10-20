CREATE TABLE `user`(
    id int primary key,
    username VARCHAR(100),
    access_failed_count int not null,
    concurrency int,
    email VARCHAR(100),
    email_confirmed VARCHAR(100),
    lockout_enabled TINYINT(0),
    lockout_end TINYINT(1),
    password_hash VARCHAR(1000),
    phone_number VARCHAR(9),
    phone_number_confirmed VARCHAR(9),
    security_stamp VARCHAR(100)
);