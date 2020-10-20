INSERT INTO `identitydailycodetest`.`gender`(gender) VALUES('Masculino');
INSERT INTO `identitydailycodetest`.`gender`(gender) VALUES('Femenino');
INSERT INTO `identitydailycodetest`.`gender`(gender) VALUES('Indefinido');

INSERT INTO `identitydailycodetest`.token(signing_key,verifier_key,additional_details) VALUES('123','123','');
INSERT INTO `identitydailycodetest`.oauth_client_details VALUES('dailyIdentity','Application for manage our tokens','dailyIdentity','$2y$12$9llNlHmk4II4G0IYoefqy.pywjK5aTwF0MnbAxPTGocGOOO/CD9Xq','ms-identity,ms-security','password,refresh_token','','',3600,3600,null,null,1);

INSERT INTO `identitydailycodetest`.`type_user`(role,description) VALUES('Dev','Developer of application');
INSERT INTO `identitydailycodetest`.`type_user`(role,description) VALUES('Client','Client of application');

