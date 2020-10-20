DROP PROCEDURE IF EXISTS update_client;
DELIMITER $$
CREATE PROCEDURE `update_client`(IN p_client_id_where VARCHAR(100),
                                 IN p_access_token_validity INT,
                                 IN p_description VARCHAR(300),
                                 IN p_additional_information VARCHAR(100),
                                 IN p_authorities VARCHAR(100),
                                 IN p_authorized_grant_types VARCHAR(100),
                                 IN p_autoapprove VARCHAR(100),
                                 IN p_client_id VARCHAR(100),
                                 IN p_refresh_token_validity INT,
                                 IN p_resource_ids VARCHAR(100),
                                 IN p_scope VARCHAR(100),
                                 IN p_web_server_redirect_uri VARCHAR(100))
BEGIN
    UPDATE oauth_client_details SET client_id = p_client_id, description = p_description, resource_ids = p_resource_ids, scope = p_scope, authorized_grant_types = p_authorized_grant_types, web_server_redirect_uri = p_web_server_redirect_uri, authorities = p_authorities, access_token_validity = p_access_token_validity,
           refresh_token_validity = p_refresh_token_validity, additional_information = p_additional_information, autoapprove = p_autoapprove WHERE client_id = p_client_id_where;
END
