ALTER TABLE user_role ADD CONSTRAINT `user_id_fk` FOREIGN KEY (user_id) REFERENCES `user`(`id`);
ALTER TABLE user_role ADD CONSTRAINT `role_id_fk` FOREIGN KEY (role_id) REFERENCES `role`(`id`);

ALTER TABLE user_claim ADD CONSTRAINT `user_id_fk2` FOREIGN KEY (user_id) REFERENCES `user`(`id`);

ALTER TABLE user_login ADD CONSTRAINT `user_id_fk3` FOREIGN KEY (user_id) REFERENCES `user`(`id`);