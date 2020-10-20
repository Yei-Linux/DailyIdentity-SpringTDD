DROP PROCEDURE IF EXISTS create_additional_information;
DELIMITER $$
CREATE PROCEDURE `create_additional_information`(IN p_additional_information VARCHAR(300),IN p_token_id INT)
BEGIN
    update token set additional_details = p_additional_information where id = p_token_id;
END
