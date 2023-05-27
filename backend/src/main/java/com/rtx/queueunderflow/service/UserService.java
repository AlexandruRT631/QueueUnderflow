package com.rtx.queueunderflow.service;

import com.rtx.queueunderflow.dto.UserDTO;
import com.rtx.queueunderflow.entity.User;
import com.rtx.queueunderflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserDTO> retrieveUsers() {
        return ((List<User>) userRepository.findAll()).stream().map(UserDTO::new).toList();
    }

    public UserDTO retrieveUserByID(Long userID) {
        Optional<User> user = userRepository.findById(userID);
        return user.map(UserDTO::new).orElse(null);
    }

    public String deleteById(Long userID) {
        try {
            userRepository.deleteById(userID);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        Optional<User> newUserGot = userRepository.findById(user.getUserId());
        if (newUserGot.isPresent()) {
            User newUser = newUserGot.get();
            newUser.replaceFields(user);
            return userRepository.save(newUser);
        } else {
            return null;
        }
    }

    public User findByeMail(String email) {
        return userRepository.findByeMail(email);
    }
}
