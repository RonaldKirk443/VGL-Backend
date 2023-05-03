package com.uio443.vglbackend.service;

import com.uio443.vglbackend.exception.EmailNotUniqueException;
import com.uio443.vglbackend.exception.UserNotFoundException;
import com.uio443.vglbackend.exception.UsernameNotUniqueException;
import com.uio443.vglbackend.model.User;
import com.uio443.vglbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}


    public User getUserByID(Long userId){
        return userRepository.findUserById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User addUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) throw new EmailNotUniqueException();
        if(userRepository.existsByUsername(user.getUsername())) throw new UsernameNotUniqueException();
        return userRepository.save(user);
    }

    public void deleteUser(Long userId){
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        userRepository.deleteUserById(userId);
    }

    public User updateUser(User user) {
        User updatedUser = userRepository.findUserById(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getId()));

        if(user.getEmail() != null && !user.getEmail().equals(updatedUser.getEmail())){
            if(userRepository.existsByEmail(user.getEmail())) throw new EmailNotUniqueException();
            updatedUser.setEmail(user.getEmail());
        }

        if(user.getUsername() != null && !user.getUsername().equals(updatedUser.getUsername())){
            if(userRepository.existsByUsername(user.getUsername())) throw new UsernameNotUniqueException();
            updatedUser.setUsername(user.getUsername());
        }

        if(user.getPfpLink() != null && !user.getPfpLink().equals(updatedUser.getPfpLink()))
            updatedUser.setPfpLink(user.getPfpLink());

        return userRepository.save(updatedUser);
    }
}
