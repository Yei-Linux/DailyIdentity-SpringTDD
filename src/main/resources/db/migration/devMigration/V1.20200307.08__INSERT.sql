INSERT INTO `user`
(`id`,
`username`,
`access_failed_count`,
`concurrency`,
`email`,
`email_confirmed`,
`lockout_enabled`,
`lockout_end`,
`password_hash`,
`phone_number`,
`phone_number_confirmed`,
`security_stamp`)
VALUES
(1,
'jesusalvan2010@gmail.com',
1,
1,
'jesusalvan2010@gmail.com',
'jesusalvan2010@gmail.com',
false,
false,
'$2y$12$9llNlHmk4II4G0IYoefqy.pywjK5aTwF0MnbAxPTGocGOOO/CD9Xq',
'123456789',
'123456789',
'');

INSERT INTO oauth_client_details VALUES('dailyChess','','$2y$12$9llNlHmk4II4G0IYoefqy.pywjK5aTwF0MnbAxPTGocGOOO/CD9Xq','ms-identity,ms-security','password,refresh_token','','',3600,3600,null,null);
INSERT INTO oauth_client_details VALUES('dailyCode','','$2y$12$dYE88EsMkaHQNNDMsnc.auEsB2.8xwpDOe/g16DNr.cFHXTNOxbv6','ms-identity,ms-security','password,refresh_token','','',3600,3600,null,null);