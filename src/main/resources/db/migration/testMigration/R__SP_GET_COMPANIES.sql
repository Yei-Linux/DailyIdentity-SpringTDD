DROP PROCEDURE IF EXISTS find_companies_all;
DELIMITER $$
CREATE PROCEDURE `find_companies_all`()
BEGIN
    select * from company;
END