CREATE TABLE `user_login`(
    provided_key VARCHAR(100) primary key,
    `login_provider` VARCHAR(100),
    user_id int,
    provided_display_name VARCHAR(100)
);