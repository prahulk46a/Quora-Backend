CREATE TABLE topic
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime     NOT NULL,
    updated_at datetime     NOT NULL,
    name       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_topic PRIMARY KEY (id)
);