ALTER TABLE `identitydailycodetest`.person ADD CONSTRAINT `gender_id_fk` FOREIGN KEY (gender_id) REFERENCES `identitydailycodetest`.gender(`id`);

ALTER TABLE `identitydailycodetest`.`user` ADD CONSTRAINT `person_id_fk` FOREIGN KEY (person_id) REFERENCES `identitydailycodetest`.person(`id`);
ALTER TABLE `identitydailycodetest`.`user` ADD CONSTRAINT `type_user_id_fk` FOREIGN KEY (type_user_id) REFERENCES `identitydailycodetest`.type_user(`id`);
ALTER TABLE `identitydailycodetest`.`user` ADD CONSTRAINT `company_id_fk` FOREIGN KEY (company_id) REFERENCES `identitydailycodetest`.company(`id`);

ALTER TABLE `identitydailycodetest`.`oauth_client_details` ADD CONSTRAINT `token_id_fk` FOREIGN KEY (token_id) REFERENCES `identitydailycodetest`.token(`id`);
ALTER TABLE `identitydailycodetest`.`company` ADD CONSTRAINT `company_token_id_fk` FOREIGN KEY (token_id) REFERENCES `identitydailycodetest`.token(`id`);