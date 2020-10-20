DROP PROCEDURE IF EXISTS get_user_details;
DELIMITER $$
CREATE PROCEDURE `get_user_details`(IN p_username VARCHAR(200))
BEGIN
    select c.id as companyId,c.name as companyName,t.id as tokenId from `user` u inner join company c on u.company_id = c.id
    inner join token t on c.token_id = t.id where u.username = p_username;
END