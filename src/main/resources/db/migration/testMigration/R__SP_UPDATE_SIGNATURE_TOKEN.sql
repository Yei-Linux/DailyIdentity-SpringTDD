DROP PROCEDURE IF EXISTS update_verifier_signing_key;
DELIMITER $$
CREATE PROCEDURE `update_verifier_signing_key`(IN p_signing_key LONGTEXT,IN p_verifier_key LONGTEXT,IN p_token_id int)
BEGIN
    update token set signing_key = p_signing_key, verifier_key = p_verifier_key where id = p_token_id;
END
