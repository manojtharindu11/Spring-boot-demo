package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return modelMapper.map(users, new TypeToken<List<UserDto>>() {}.getType());
    }

    public UserDto addUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepo.save(user);
        return userDto;
    }

    public UserDto updateUser(UserDto userDto) {
        User user = userRepo.findById(userDto.getId()).get();
        user.setName(userDto.getName());
        return userDto;
    }
}
