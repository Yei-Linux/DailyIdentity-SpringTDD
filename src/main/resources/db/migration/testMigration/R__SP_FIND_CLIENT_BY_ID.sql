DROP PROCEDURE IF EXISTS find_client_by_id;
DELIMITER $$
CREATE PROCEDURE `find_client_by_id`(IN p_client_id VARCHAR(100))
BEGIN
    select * from oauth_client_details where client_id = p_client_id;
END
