DROP PROCEDURE IF EXISTS find_by_user_name;
DELIMITER $$
CREATE PROCEDURE `find_by_user_name`(IN p_username VARCHAR(100))
BEGIN
    select username,phone_number,password_hash from user where username = p_username;
END
