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

    public List<User> retrieveUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User retrieveUserByID(Long userID) {
        Optional<User> user = userRepository.findById(userID);
        if(user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
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
}
