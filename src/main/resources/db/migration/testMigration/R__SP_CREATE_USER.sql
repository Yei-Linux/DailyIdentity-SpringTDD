DROP PROCEDURE IF EXISTS create_user;
DELIMITER $$
CREATE PROCEDURE `create_user`(IN p_username VARCHAR(200),
                               IN p_phone_number VARCHAR(9),
                               IN p_password VARCHAR(200),
                               IN p_type_user_id INT,
                               IN p_person_id int,
                               IN p_company_id int)
BEGIN
    INSERT INTO `identitydailycodetest`.`user`
    (`username`,
    `access_failed_count`,
    `concurrency`,
    `email`,
    `email_confirmed`,
    `lockout_enabled`,
    `lockout_end`,
    `password_hash`,
    `phone_number`,
    `phone_number_confirmed`,
    `security_stamp`,
    `person_id`,
    type_user_id,
    company_id)
    VALUES
    (p_username,
    1,
    1,
    p_username,
    p_username,
    false,
    false,
    p_password,
    p_phone_number,
    p_phone_number,
    '',
    p_person_id,
    p_type_user_id,
    p_company_id);

END