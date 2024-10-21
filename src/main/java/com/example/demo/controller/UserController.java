package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping(value = "api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getusers")
    public List<UserDto> getUser() {
        return userService.getAllUsers();
    }

    @PostMapping("/adduser")
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }
}
