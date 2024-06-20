ALTER TABLE topic_questions
DROP
FOREIGN KEY fk_topque_on_question;

ALTER TABLE topic_questions
DROP
FOREIGN KEY fk_topque_on_topic;

CREATE TABLE question_topics
(
    questions_id BIGINT NOT NULL,
    topics_id    BIGINT NOT NULL
);

ALTER TABLE question_topics
    ADD CONSTRAINT fk_quetop_on_question FOREIGN KEY (questions_id) REFERENCES question (id);

ALTER TABLE question_topics
    ADD CONSTRAINT fk_quetop_on_topic FOREIGN KEY (topics_id) REFERENCES topic (id);

DROP TABLE topic_questions;