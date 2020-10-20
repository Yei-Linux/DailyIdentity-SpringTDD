DROP PROCEDURE IF EXISTS create_person;
DELIMITER $$
CREATE PROCEDURE `create_person`( OUT p_result int,
                                  IN p_first_name VARCHAR(200),
                                  IN p_middle_name VARCHAR(200),
                                  IN p_last_name VARCHAR(200),
                                  IN p_age INT,
                                  IN p_gender_id INT,
                                  IN p_address VARCHAR(200))
BEGIN
    INSERT INTO `identitydailycodetest`.`person`(first_name,middle_name,last_name,age,gender_id,address)
    VALUES(p_first_name,p_middle_name,p_last_name,p_age,p_gender_id,p_address);
    SET p_result = LAST_INSERT_ID();
END