package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.Models.User;
import com.udacity.jwdnd.course1.cloudstorage.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public boolean registerUser(User user){
        if(userRepository.existsByUsername(user.getUsername())){
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public boolean authenticate(String username, String password){
        return userRepository.existsByUsernameAndPassword(username, password);
    }
}
