DROP PROCEDURE IF EXISTS delete_client_by_id;
DELIMITER $$
CREATE PROCEDURE `delete_client_by_id`(IN p_client_id VARCHAR(100))
BEGIN
    delete from oauth_client_details where client_id = p_client_id;
END
