DROP PROCEDURE IF EXISTS get_value_of_dynamical_field;
DELIMITER $$
CREATE PROCEDURE `get_value_of_dynamical_field`(IN p_dynamical_field VARCHAR(100),
                                                IN p_username VARCHAR(100))
BEGIN
    SET @sql = Concat('SELECT person.',p_dynamical_field,' into @valueField FROM user INNER JOIN person ON user.person_id = person.id WHERE user.username="',p_username,'"');
	PREPARE stmtSql from @sql;
    Execute stmtSql;
    Deallocate PREPARE stmtSql;

    SELECT @valueField as generalValue;
END