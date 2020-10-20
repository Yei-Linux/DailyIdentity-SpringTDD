DROP PROCEDURE IF EXISTS find_verifier_signing_key;
DELIMITER $$
CREATE PROCEDURE `find_verifier_signing_key`()
BEGIN
    select signing_key as signingKey ,verifier_key as verifierKey from token;
END
