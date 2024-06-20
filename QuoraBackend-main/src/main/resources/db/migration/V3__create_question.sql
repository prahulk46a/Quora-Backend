CREATE TABLE question
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime     NOT NULL,
    updated_at    datetime     NOT NULL,
    title         VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    user_id       BIGINT NULL,
    CONSTRAINT pk_question PRIMARY KEY (id)
);

ALTER TABLE question
    ADD CONSTRAINT FK_QUESTION_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);