package me.Rahul.quorabackend.adapters;

import jakarta.persistence.EntityNotFoundException;
import me.Rahul.quorabackend.entities.Question;
import me.Rahul.quorabackend.entities.Topic;
import me.Rahul.quorabackend.entities.User;
import me.Rahul.quorabackend.repositories.TopicRepository;
import me.Rahul.quorabackend.repositories.UserRepository;
import me.Rahul.quorabackend.dtos.CreateQuestionDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CreateQuestionDtoToQuestionAdapterImpl implements CreateQuestionDtoToQuestionAdapter{
    private UserRepository userRepository;
    private TopicRepository topicRepository;

    public CreateQuestionDtoToQuestionAdapterImpl(UserRepository userRepository, TopicRepository topicRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }
    @Override
    public Question convertDTO(CreateQuestionDTO createQuestionDTO) throws Exception{
        try {
            Optional<User> user = this.userRepository.findById(createQuestionDTO.getUserId());
            if(user.isEmpty()){
                throw new EntityNotFoundException("User not found with id " + createQuestionDTO.getUserId());
            }
            List<Long> topicIds = createQuestionDTO.getTopics();
            List<Topic> topics = new ArrayList<>();
            if(!topicIds.isEmpty()){
                for(Long topicId : topicIds){
                    Topic topic = this.topicRepository.findById(topicId).orElseThrow(EntityNotFoundException::new);
                    topics.add(topic);
                }
            }
            Question question = Question.builder()
                                .title(createQuestionDTO.getTitle())
                                .description(createQuestionDTO.getDescription())
                                .user(user.get())
                                .topics(topics)
                                .build();
            return question;
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
