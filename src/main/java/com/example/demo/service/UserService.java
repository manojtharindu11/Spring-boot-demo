package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto updateUser(UserDto userDto) {
        User user = userRepo.findById(userDto.getId()).get();
        user.setName(userDto.getName());
        return userDto;
    }

    public String deleteUser(int id) {
        User user = userRepo.findById(id).get();
        userRepo.delete(user);
        return "User deleted";
    }

    public UserDto getUser(int id) {
        User user = userRepo.findById(id).get();
        return modelMapper.map(user, UserDto.class);
    }

}
