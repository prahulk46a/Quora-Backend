CREATE TABLE topic_questions
(
    questions_id BIGINT NOT NULL,
    topics_id    BIGINT NOT NULL
);

ALTER TABLE topic_questions
    ADD CONSTRAINT fk_topque_on_question FOREIGN KEY (questions_id) REFERENCES question (id);

ALTER TABLE topic_questions
    ADD CONSTRAINT fk_topque_on_topic FOREIGN KEY (topics_id) REFERENCES topic (id);