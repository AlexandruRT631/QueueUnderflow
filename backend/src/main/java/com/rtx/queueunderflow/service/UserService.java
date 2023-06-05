package com.rtx.queueunderflow.service;

import com.rtx.queueunderflow.dto.UserDTO;
import com.rtx.queueunderflow.entity.User;
import com.rtx.queueunderflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    JavaMailSender javaMailSender;

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

    public UserDTO banUserById(Long userID) {
        Optional<User> user = userRepository.findById(userID);
        if (user.isPresent()) {
            User newUser = user.get();
            newUser.setBanned(!newUser.isBanned());
            if (newUser.isBanned()) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(newUser.geteMail());
                message.setSubject("You have been banned");
                message.setText("You have been banned from QueueUnderflow");
                javaMailSender.send(message);
            }
            return new UserDTO(userRepository.save(newUser));
        } else {
            return null;
        }
    }

    public User findByeMail(String email) {
        return userRepository.findByeMail(email);
    }
}
