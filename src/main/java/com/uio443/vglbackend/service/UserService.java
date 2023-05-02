package com.uio443.vglbackend.service;

import com.uio443.vglbackend.model.User;
import com.uio443.vglbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}


    public User getUserByID(Long id){
        return userRepository.findUserById(id).orElseThrow(() -> new RuntimeException("Bad id"));
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
