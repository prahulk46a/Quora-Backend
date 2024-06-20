package me.Rahul.quorabackend.controllers;

import me.Rahul.quorabackend.entities.User;
import me.Rahul.quorabackend.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User request) {
        try {
            if(request.getUsername().isEmpty() || request.getEmail().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or email is required");
            }
            User user = this.userService.registerUser(request);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        try {
            Optional<User> user = this.userService.getUserById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserDetails(@PathVariable Long userId, @RequestBody User newUserData) {
        try {
            User updatedUser = this.userService.updateUserDetails(userId, newUserData);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{userId}/follow/{targetUserId}")
    public ResponseEntity<?> addFollower(@PathVariable Long userId, @PathVariable Long targetUserId) {
        try {
            Boolean response = this.userService.addFollower(userId, targetUserId);
            if(!response){
                return new ResponseEntity<>("Unable to follow right now try later",HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("Successfully followed user: "+ targetUserId, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
