ALTER TABLE answer
    ADD question_id BIGINT NULL;

ALTER TABLE answer
    ADD CONSTRAINT FK_ANSWER_ON_QUESTION FOREIGN KEY (question_id) REFERENCES question (id);