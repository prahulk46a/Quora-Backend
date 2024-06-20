CREATE TABLE user_follower
(
    user_id     BIGINT NOT NULL,
    follower_id BIGINT NOT NULL
);

CREATE TABLE user_following
(
    user_id      BIGINT NOT NULL,
    following_id BIGINT NOT NULL
);

ALTER TABLE user_follower
    ADD CONSTRAINT fk_usefol_on_follower FOREIGN KEY (follower_id) REFERENCES user (id);

ALTER TABLE user_following
    ADD CONSTRAINT fk_usefol_on_following FOREIGN KEY (following_id) REFERENCES user (id);

ALTER TABLE user_following
    ADD CONSTRAINT fk_usefol_on_user FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_follower
    ADD CONSTRAINT fk_usefol_on_userBgdFMK FOREIGN KEY (user_id) REFERENCES user (id);