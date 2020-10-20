DROP PROCEDURE IF EXISTS get_additional_information_fields;
DELIMITER $$
CREATE PROCEDURE `get_additional_information_fields`()
BEGIN
    SELECT `COLUMN_NAME` as field FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE `TABLE_SCHEMA`='identitydailycodetest' AND `TABLE_NAME`='person';
END