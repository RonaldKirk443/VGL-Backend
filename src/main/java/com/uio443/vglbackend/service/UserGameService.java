package com.uio443.vglbackend.service;

import com.uio443.vglbackend.enums.CompletionStatus;
import com.uio443.vglbackend.exception.UserGameNotFoundException;
import com.uio443.vglbackend.exception.UserNotFoundException;
import com.uio443.vglbackend.enums.HiddenStatus;
import com.uio443.vglbackend.model.UserGame;
import com.uio443.vglbackend.repository.UserGameRepository;
import com.uio443.vglbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserGameService {
    private final UserGameRepository userGameRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserGameService(UserGameRepository userGameRepository, UserRepository userRepository) {
        this.userGameRepository = userGameRepository;
        this.userRepository = userRepository;
    }

    public UserGame addGame(Long userId, UserGame userGame) {
        if(userGame.getIgdbId() == -1) throw new RuntimeException("Game id not passed");
        userGame.setUser(userRepository.findUserById(userId).orElseThrow(() -> new UserNotFoundException(userId)));
        if(userGameRepository.existsByUserIdIgdbId(userId, userGame.getIgdbId())) throw new RuntimeException("Game existo");
        return userGameRepository.save(userGame);
    }

    public List<UserGame> getGameList(Long userId) {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        return userGameRepository.getAllGamesByUserId(userId);
    }

    public UserGame getGame(Long userId, Long igdbId){
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        return userGameRepository.getGameByUserIdIgdbId(userId, igdbId).orElseThrow(() -> new UserGameNotFoundException(userId, igdbId));
    }

    public void deleteGame(Long userId, Long igdbId){
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        if(!userGameRepository.existsByUserIdIgdbId(userId, igdbId)) throw new UserGameNotFoundException(userId, igdbId);
        userGameRepository.deleteGame(userId, igdbId);
    }

    public UserGame updateGame(Long userId, UserGame userGame) {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        UserGame newUserGame = userGameRepository.getGameByUserIdIgdbId(userId, userGame.getIgdbId())
                .orElseThrow(() -> new UserGameNotFoundException(userId, userGame.getIgdbId()));

        if(userGame.getCompletionStatus() != CompletionStatus.Default && !userGame.getCompletionStatus().equals(newUserGame.getCompletionStatus())){
            newUserGame.setCompletionStatus(userGame.getCompletionStatus());
        }

        if(userGame.getHoursPlayed() != 0 && userGame.getHoursPlayed() != newUserGame.getHoursPlayed()){
            newUserGame.setHoursPlayed(userGame.getHoursPlayed());
        }

        if(userGame.getHiddenStatus() != HiddenStatus.Default && !userGame.getHiddenStatus().equals(newUserGame.getHiddenStatus())){
            newUserGame.setHiddenStatus(userGame.getHiddenStatus());
        }

        return userGameRepository.save(newUserGame);
    }
}
