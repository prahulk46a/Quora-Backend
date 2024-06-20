CREATE TABLE IF NOT EXISTS user
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    username   VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);