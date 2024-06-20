package me.Rahul.quorabackend.services;

import jakarta.persistence.EntityNotFoundException;
import me.Rahul.quorabackend.repositories.UserRepository;
import me.Rahul.quorabackend.entities.User;
import org.hibernate.FetchNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(Long id) throws EntityNotFoundException {
        try {
            Optional<User> user = this.userRepository.findById(id);
            if(user.isEmpty()){
                throw new EntityNotFoundException("User with id " + id + " not found");
            }
            return user;
        }catch (Exception e) {
            if(e instanceof EntityNotFoundException){
                throw new EntityNotFoundException("User with id " + id + " not found");
            }
            throw new FetchNotFoundException("Unable to fetch User with id " + id, id);
        }
    }

    @Override
    public User registerUser(User user) throws Exception{
        try {
            return this.userRepository.save(user);
        }catch (Exception e){
            if(e.getClass() == DataIntegrityViolationException.class){
                throw new DataIntegrityViolationException("email already exists, please try with another email");
            }
            throw new Exception("Unable to register, Please try again later");
        }
    }

    @Override
    public User updateUserDetails(Long userId, User userData) {
        try {
            User updatedUser = this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
            if(userData.getUsername() != null){
                updatedUser.setUsername(userData.getUsername());
            }
            if(userData.getEmail() != null){
                updatedUser.setEmail(userData.getEmail());
            }
            if(userData.getBio() != null){
                updatedUser.setBio(userData.getBio());
            }
            return this.userRepository.save(updatedUser);
        }catch (Exception e){
            e.printStackTrace();
            if(e.getClass() == DataIntegrityViolationException.class){
                throw new DataIntegrityViolationException("email already exists, please try with another email");
            }
            throw new EntityNotFoundException("Unable to update user details");
        }
    }

    @Override
    public Boolean addFollower(Long userId, Long targetUserId) throws Exception {
        try {
            User user = this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
            User targetUserToFollow = this.userRepository.findById(targetUserId).orElseThrow(EntityNotFoundException::new);
            targetUserToFollow.getFollower().add(user);
            user.getFollowing().add(targetUserToFollow);
            this.userRepository.save(user);
            this.userRepository.save(targetUserToFollow);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
