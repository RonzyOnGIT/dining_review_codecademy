package com.diningreview.dining.services;

import com.diningreview.dining.repositories.UserRepository;

import java.util.Optional;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.diningreview.dining.entities.User;
import com.diningreview.dining.exceptions.UsernameAlreadyExistsException;


// this class will handle all of the business logic i.e. checking for user to see if authenticated and stuff
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User getUser(String userName) {
        Optional<User> userOptional = this.userRepository.findByUserName(userName);

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User: " + userName + " was not found");
        }

        return userOptional.get();

    }
    
    // returns false either if user is not found or if not a moderator
    public Boolean checkIfUserIsModerator(String userName) {
        Optional<User> userOptional = this.userRepository.findByUserName(userName);

        // user not found
        if (!userOptional.isPresent()) {
            return false;
        }

        User retrievedUser = userOptional.get();

        return retrievedUser.getIsModerator();

    }

    public User createUser(User user) {

        // only create a new user if username not found
        Optional<User> userOptional = this.userRepository.findByUserName(user.getUserName());

        // user with same username found, do not create a new user
        try {

            if (userOptional.isPresent()) {
                throw new UsernameAlreadyExistsException("user name: " + user.getUserName() + " already exists");
            }

            User newUser = this.userRepository.save(user);
            return newUser;

        } catch (UsernameAlreadyExistsException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }

    
    }

    public User updateUser(User user) {
        
        Optional<User> retrievedUserOptional = this.userRepository.findByUserName(user.getUserName());

        // user was not found
        if (!retrievedUserOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username: " + user.getUserName() + " was not found");
        }

        User userToUpdate = retrievedUserOptional.get();

        // only update the fields that were not passed in as null from request
        if (user.getCity() != null) {
            userToUpdate.setCity(user.getCity());
        }

        if (user.getState() != null) {
            userToUpdate.setState(user.getState());
        }

        if (user.getZipCode() != null) {
            userToUpdate.setZipCode(user.getZipCode());
        }

        return new User();
    }
    
}
