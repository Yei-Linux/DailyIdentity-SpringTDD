CREATE TABLE `user_claim`(
    id int primary key,
    `user_id` int,
    claim_type VARCHAR(100),
    claim_value VARCHAR(100)
);