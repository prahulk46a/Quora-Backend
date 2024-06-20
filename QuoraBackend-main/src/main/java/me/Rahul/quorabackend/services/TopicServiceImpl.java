package me.Rahul.quorabackend.services;

import me.Rahul.quorabackend.repositories.TopicRepository;
import
        org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {
    private TopicRepository topicRepository;
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

}
