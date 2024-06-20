package me.Rahul.quorabackend.adapters;

import jakarta.persistence.EntityNotFoundException;
import me.Rahul.quorabackend.entities.Answer;
import me.Rahul.quorabackend.entities.User;
import me.Rahul.quorabackend.repositories.UserRepository;
import me.Rahul.quorabackend.dtos.CreateAnswerDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateAnswerDtoToAnswerAdapterImpl implements CreateAnswerDtoToAnswerAdapter {

    private UserRepository userRepository;

    public CreateAnswerDtoToAnswerAdapterImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Answer convertDto(CreateAnswerDTO createAnswerDTO) throws Exception {
        try {
            User user = this.userRepository.findById(createAnswerDTO.getUserId()).orElseThrow(EntityNotFoundException::new);
            return Answer.builder().text(createAnswerDTO.getText()).user(user).build();
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
