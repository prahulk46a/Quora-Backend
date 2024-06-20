package me.Rahul.quorabackend.services;

import me.Rahul.quorabackend.entities.User;

import java.util.Optional;

public interface IUserService {
    public Optional<User> getUserById(Long id);
    public User registerUser(User user) throws Exception;
    public User updateUserDetails(Long userId, User user)throws Exception;
    public Boolean addFollower(Long userId, Long targetUserId)throws Exception;
}
