CREATE TABLE answer
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime     NOT NULL,
    updated_at datetime     NOT NULL,
    text       VARCHAR(255) NOT NULL,
    user_id    BIGINT       NOT NULL,
    CONSTRAINT pk_answer PRIMARY KEY (id)
);

ALTER TABLE answer
    ADD CONSTRAINT uc_answer_user UNIQUE (user_id);

ALTER TABLE answer
    ADD CONSTRAINT FK_ANSWER_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);