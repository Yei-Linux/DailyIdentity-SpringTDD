DROP PROCEDURE IF EXISTS get_additional_information;
DELIMITER $$
CREATE PROCEDURE `get_additional_information`(IN p_token_id int)
BEGIN
    SELECT additional_details as generalValue FROM token where id = p_token_id;
END