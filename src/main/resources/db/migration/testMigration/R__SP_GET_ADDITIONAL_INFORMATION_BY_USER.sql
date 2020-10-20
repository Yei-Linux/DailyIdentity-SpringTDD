DROP PROCEDURE IF EXISTS get_additional_information_by_user;
DELIMITER $$
CREATE PROCEDURE `get_additional_information_by_user`(IN p_username VARCHAR(100))
BEGIN
	DECLARE countIteration INTEGER DEFAULT 0;
    DROP TABLE IF EXISTS temporalTable;
	CREATE TEMPORARY TABLE temporalTable(field varchar(100),`value` VARCHAR(100));
    SET @size := (SELECT COUNT(`COLUMN_NAME`) FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE `TABLE_SCHEMA`='identitydailycodetest' AND `TABLE_NAME`='person');

   WHILE countIteration < @size DO
		SET @field := (SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE `TABLE_SCHEMA`='identitydailycodetest' AND `TABLE_NAME`='person' LIMIT 1 OFFSET countIteration);

        Begin
			SET @sql = Concat('SELECT person.',@field,' FROM user INNER JOIN person ON user.person_id = person.id WHERE user.username = "',p_username,'" INTO @valueField');
			PREPARE stmtSql from @sql;
			Execute stmtSql;
            Deallocate PREPARE stmtSql;
		End;

        INSERT INTO temporalTable VALUES(@field,@valueField);
        SET countIteration = countIteration+1;
	END WHILE;

    SELECT * FROM temporalTable;
END