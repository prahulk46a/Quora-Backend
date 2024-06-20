ALTER TABLE topic
    ADD CONSTRAINT uc_topic_name UNIQUE (name);