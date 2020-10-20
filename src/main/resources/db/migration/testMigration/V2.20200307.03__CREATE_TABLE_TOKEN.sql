CREATE TABLE `identitydailycodetest`.`token`(
    id int AUTO_INCREMENT primary key,
    signing_key LONGTEXT,
    `verifier_key` LONGTEXT,
    additional_details TEXT
);