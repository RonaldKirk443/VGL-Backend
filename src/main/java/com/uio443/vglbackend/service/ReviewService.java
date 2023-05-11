package com.uio443.vglbackend.service;


import com.uio443.vglbackend.exception.GameNotFoundException;
import com.uio443.vglbackend.exception.UserGameNotFoundException;
import com.uio443.vglbackend.exception.UserNotFoundException;
import com.uio443.vglbackend.model.UserGame;
import com.uio443.vglbackend.repository.GameRepository;
import com.uio443.vglbackend.repository.UserGameRepository;
import com.uio443.vglbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final UserRepository userRepository;
    private final UserGameRepository userGameRepository;

    @Autowired
    public ReviewService(UserRepository userRepository, UserGameRepository userGameRepository) {
        this.userRepository = userRepository;
        this.userGameRepository = userGameRepository;
    }

    public double getUserRating(Long userId, Long igdbId){
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        UserGame userGame = userGameRepository.getGameByUserIdIgdbId(userId, igdbId).orElseThrow(() -> new GameNotFoundException(igdbId));
        return userGame.getRating();
    }

    public double getGameRating(Long igdbId){
        List<UserGame> userGameList = userGameRepository.getAllUserGamesByIgdbId(igdbId);
        int counter = 0;
        double avg = 0;
        for (UserGame u : userGameList){
            if(u.getRating() != -1){
                counter++;
                avg += u.getRating();
            }
        }
        avg = avg / counter;
        return avg;
    }

    public String getReviewComment(Long userId, Long igdbId) {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        UserGame userGame = userGameRepository.getGameByUserIdIgdbId(userId, igdbId).orElseThrow(() -> new GameNotFoundException(igdbId));
        return userGame.getReview();
    }

    public List<Object> getReviewListByGame(Long igdbId) {
        return userGameRepository.getAllReviewsByigdbId(igdbId);
    }

    public List<Object> getReviewListByUser(Long userId) {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        return userGameRepository.getAllReviewsByUserId(userId);
    }

    public void addRating(Long userId, Long igdbId, double rating){
        UserGame newUserGame = userGameRepository.getGameByUserIdIgdbId(userId, igdbId)
                .orElseThrow(() -> new UserGameNotFoundException(userId, igdbId));
        // I AM NOT CHECKING TO MAKE SURE THE REVIEW DOES NOT EXIST
        // This means that this method can be used to update the review as well
        // Can be changed by uncommenting the next line
        // if(newUserGame.getRating() != -1) throw new RuntimeException("User already set a rating for this game");
        if (rating != -1 && newUserGame.getRating() != rating){
            newUserGame.setRating(rating);
        }
        userGameRepository.save(newUserGame);

    }

    public void addReviewComment(Long userId, Long igdbId, String reviewComment) {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        UserGame newUserGame = userGameRepository.getGameByUserIdIgdbId(userId, igdbId)
                .orElseThrow(() -> new UserGameNotFoundException(userId, igdbId));
        // I AM NOT CHECKING TO MAKE SURE THE REVIEW DOES NOT EXIST
        // This means that this method can be used to update the review as well
        // Can be changed by uncommenting the next line
        //if(newUserGame.getReview() != null) throw new RuntimeException("A review already exists");
        if (reviewComment != null){
            newUserGame.setReview(reviewComment);
        }
        userGameRepository.save(newUserGame);
    }

    public void updateRating(Long userId, Long igdbId, double rating){
        UserGame newUserGame = userGameRepository.getGameByUserIdIgdbId(userId, igdbId)
                .orElseThrow(() -> new UserGameNotFoundException(userId, igdbId));
        if(newUserGame.getRating() == -1) throw new RuntimeException("User does not have a rating for this game");
        if (rating != -1 && newUserGame.getRating() != rating){
            newUserGame.setRating(rating);
        }
        userGameRepository.save(newUserGame);

    }

    public void updateReviewComment(Long userId, Long igdbId, String reviewComment) {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        UserGame newUserGame = userGameRepository.getGameByUserIdIgdbId(userId, igdbId)
                .orElseThrow(() -> new UserGameNotFoundException(userId, igdbId));
        if(newUserGame.getReview() == null) throw new RuntimeException("Review does not exist");

        if (reviewComment != null && !newUserGame.getReview().equals(reviewComment)){
            newUserGame.setReview(reviewComment);
        }
        userGameRepository.save(newUserGame);
    }

    public void deleteReviewComment(Long userId, Long igdbId) {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        UserGame newUserGame = userGameRepository.getGameByUserIdIgdbId(userId, igdbId)
                .orElseThrow(() -> new UserGameNotFoundException(userId, igdbId));
        if(newUserGame.getReview() == null) throw new RuntimeException("Review does not exist");
        newUserGame.setReview(null);
        userGameRepository.save(newUserGame);
    }

    public void deleteAllGameReviewComments(Long igdbId) {
        List<UserGame> userGameList = userGameRepository.getAllUserGamesByIgdbId(igdbId);
        for (UserGame u : userGameList){
            u.setReview(null);
            userGameRepository.save(u);
        }
    }

    public void deleteAllUserReviewComments(Long userId) {
        List<UserGame> userGameList = userGameRepository.getAllUserGamesByUserId(userId);
        for (UserGame u : userGameList){
            u.setReview(null);
            userGameRepository.save(u);
        }
    }
}
