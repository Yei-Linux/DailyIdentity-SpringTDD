DROP PROCEDURE IF EXISTS insert_client;
DELIMITER $$
CREATE PROCEDURE `insert_client`(IN p_access_token_validity INT,
                                 IN p_additional_information VARCHAR(100),
                                 IN p_description VARCHAR(100),
                                 IN p_authorities VARCHAR(100),
                                 IN p_authorized_grant_types VARCHAR(100),
                                 IN p_autoapprove VARCHAR(100),
                                 IN p_client_id VARCHAR(100),
                                 IN p_client_secret VARCHAR(100),
                                 IN p_refresh_token_validity INT,
                                 IN p_resource_ids VARCHAR(100),
                                 IN p_scope VARCHAR(100),
                                 IN p_web_server_redirect_uri VARCHAR(100),
                                 IN p_token_id int)
BEGIN
    INSERT INTO oauth_client_details VALUES(p_client_id,p_description,p_resource_ids,p_client_secret,p_scope,p_authorized_grant_types,p_web_server_redirect_uri,p_authorities,p_access_token_validity,p_refresh_token_validity,p_additional_information,p_autoapprove,p_token_id);
END
