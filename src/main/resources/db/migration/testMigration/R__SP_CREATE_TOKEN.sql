DROP PROCEDURE IF EXISTS create_token;
DELIMITER $$
CREATE PROCEDURE `create_token`(OUT p_result INT)
BEGIN
   INSERT INTO token(signing_key,verifier_key,additional_details) VALUES('123','123','[{"field":"id","value":"null"}]');
        SET p_result = LAST_INSERT_ID();
END
