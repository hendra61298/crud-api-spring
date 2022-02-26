package com.film.moviecrudapi.service;

import com.film.moviecrudapi.common.helper.Helpers;
import com.film.moviecrudapi.database.model.User;
import com.film.moviecrudapi.database.repository.UserRepository;
import com.film.moviecrudapi.dto.auth.CreateUserDto;
import com.film.moviecrudapi.dto.auth.LoginUserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(CreateUserDto createUserDto) {
        User userExist = userRepository.findByEmail(createUserDto.email);
        if (userExist == null) {
            User user = new User(createUserDto.name, createUserDto.email, createUserDto.role,
                    Helpers.bycrptEncoderPassword(createUserDto.password));
            return userRepository.save(user);
        } else {
            return null;
        }

    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getById(String id) {
        return userRepository.findById(id);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User deletedUser(User user) {
        userRepository.delete(user);
        return user;
    }

    public User login(LoginUserDto loginUserDto) {
        User userExist = userRepository.findByEmail(loginUserDto.email);
        if (userExist != null) {
            if (Helpers.bycrptMatchingPassword(loginUserDto.password, userExist.getPassword())) {
                return userExist;
            }
            return null;
        } else {
            return null;
        }
    }

    public User findUserById(String id) {
        User user = userRepository.findById(id);
        if (user != null) {
            return user;
        }
        return null;
    }

}
