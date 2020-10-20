DROP PROCEDURE IF EXISTS create_company;
DELIMITER $$
CREATE PROCEDURE `create_company`(OUT p_result INT,IN p_name VARCHAR(200),IN p_token_id INT)
BEGIN
    INSERT INTO company(name,token_id) VALUES(p_name,p_token_id);
    SET p_result = LAST_INSERT_ID();
END