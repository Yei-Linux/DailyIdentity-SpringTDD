DROP PROCEDURE IF EXISTS find_clients_all;
DELIMITER $$
CREATE PROCEDURE `find_clients_all`(IN p_token_id int)
BEGIN
    select * from oauth_client_details where token_id = p_token_id;
END
