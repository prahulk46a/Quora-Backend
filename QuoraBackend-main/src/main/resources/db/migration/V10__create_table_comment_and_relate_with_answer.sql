CREATE TABLE answer_comments
(
    answer_id   BIGINT NOT NULL,
    comments_id BIGINT NOT NULL
);

CREATE TABLE comment
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NOT NULL,
    updated_at datetime              NOT NULL,
    content    VARCHAR(255)          NOT NULL,
    user_id    BIGINT                NOT NULL,
    answer_id  BIGINT                NULL,
    CONSTRAINT pk_comment PRIMARY KEY (id)
);

ALTER TABLE answer_comments
    ADD CONSTRAINT uc_answer_comments_comments UNIQUE (comments_id);

ALTER TABLE comment
    ADD CONSTRAINT uc_comment_user UNIQUE (user_id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_ANSWER FOREIGN KEY (answer_id) REFERENCES answer (id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE answer_comments
    ADD CONSTRAINT fk_anscom_on_answer FOREIGN KEY (answer_id) REFERENCES answer (id);

ALTER TABLE answer_comments
    ADD CONSTRAINT fk_anscom_on_comment FOREIGN KEY (comments_id) REFERENCES comment (id);