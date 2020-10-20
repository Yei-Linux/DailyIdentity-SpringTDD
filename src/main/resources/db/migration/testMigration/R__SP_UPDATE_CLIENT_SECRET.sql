DROP PROCEDURE IF EXISTS update_client_secret;
DELIMITER $$
CREATE PROCEDURE `update_client_secret`(IN p_client_id_where VARCHAR(100),
                                 IN p_client_secret VARCHAR(100))
BEGIN
    UPDATE oauth_client_details SET client_secret = p_client_secret WHERE client_id = p_client_id_where;
END
