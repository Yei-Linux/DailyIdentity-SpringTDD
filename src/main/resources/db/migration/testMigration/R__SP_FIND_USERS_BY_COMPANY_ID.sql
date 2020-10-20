DROP PROCEDURE IF EXISTS find_users_by_company_id;
DELIMITER $$
CREATE PROCEDURE `find_users_by_company_id`(IN p_company_id VARCHAR(100))
BEGIN
    select u.username,u.phone_number,
    CONCAT(p.first_name,' ',p.middle_name,' ',p.last_name) as fullname,
    p.age as age
    from user u inner join person p on u.person_id = p.id where u.company_id = p_company_id;
END
