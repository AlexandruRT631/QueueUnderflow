package com.rtx.queueunderflow.controller;

import com.rtx.queueunderflow.dto.UserDTO;
import com.rtx.queueunderflow.entity.User;
import com.rtx.queueunderflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/insertUser")
    @ResponseBody
    public User insertUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<User> retrieveUsers() {
        return userService.retrieveUsers();
    }

    @GetMapping("/getById/{user_id}")
    @ResponseBody
    public UserDTO retrieveById(@PathVariable Long user_id) {
        User user = userService.retrieveUserByID(user_id);
        return new UserDTO(user.getFirstName(), user.getLastName());
    }

    @GetMapping("/getById")
    @ResponseBody
    public UserDTO retrieveById1(@RequestParam("user_id") Long user_id) {
        User user = userService.retrieveUserByID(user_id);
        return new UserDTO(user.getFirstName(), user.getLastName());
    }

    @PutMapping("/updateUser")
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        User oldUser = userService.retrieveUserByID(user.getUserId());
        user.replaceNullFields(oldUser);
        return userService.saveUser(user);
    }

    @DeleteMapping("/deleteById/{user_id}")
    @ResponseBody
    public String deleteById(@PathVariable Long user_id) {
        return userService.deleteById(user_id);
    }
}
