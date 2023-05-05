package com.uio443.vglbackend.service;


import com.uio443.vglbackend.exception.GameNotFoundException;
import com.uio443.vglbackend.exception.UserNotFoundException;
import com.uio443.vglbackend.model.Review;
import com.uio443.vglbackend.repository.GameRepository;
import com.uio443.vglbackend.repository.ReviewRepository;
import com.uio443.vglbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, GameRepository gameRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    public Review addReview(Long userId, Long igdbId, Review review) {
        review.setUser(userRepository.findUserById(userId).orElseThrow(() -> new UserNotFoundException(userId)));
        review.setGame(gameRepository.findGameByigdbId(igdbId).orElseThrow(() -> new GameNotFoundException(igdbId)));
        if(reviewRepository.reviewExistsByUserIdIgdbId(userId, igdbId)) throw new RuntimeException("Review already exists");
        return reviewRepository.save(review);
    }

    public Review getReview(Long userId, Long igdbId) {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        if(!gameRepository.existsById(igdbId)) throw new GameNotFoundException(igdbId);
        return reviewRepository.getReviewByUserIdIgdbId(userId, igdbId).orElseThrow(() -> new RuntimeException("Review does not exist"));
    }

    public List<Review> getReviewListByGame(Long igdbId) {
        if(!gameRepository.existsById(igdbId)) throw new GameNotFoundException(igdbId);
        return reviewRepository.getAllReviewsByigdbId(igdbId);
    }

    public List<Review> getReviewListByUser(Long userId) {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        return reviewRepository.getAllReviewsByUserId(userId);
    }

    public Review updateReview(Long userId, Long igdbId, Review review) {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        if(!gameRepository.existsById(igdbId)) throw new GameNotFoundException(igdbId);
        Review newReview = reviewRepository.getReviewByUserIdIgdbId(userId, igdbId)
                .orElseThrow(() -> new RuntimeException("Review does not exist"));

        if(review.getCommentText() != null && !review.getCommentText().equals(newReview.getCommentText())) {
            newReview.setCommentText(review.getCommentText());
        }

        if(review.getRating() != -1 && review.getRating() != newReview.getRating()) {
            newReview.setRating(review.getRating());
        }

        return reviewRepository.save(newReview);
    }

    public void deleteReview(Long userId, Long igdbId) {
        if(!userRepository.existsById(userId)) throw new UserNotFoundException(userId);
        if(!gameRepository.existsById(igdbId)) throw new GameNotFoundException(igdbId);
        if(!reviewRepository.reviewExistsByUserIdIgdbId(userId, igdbId)) throw new RuntimeException("Review does not exists");
        reviewRepository.deleteReview(userId, igdbId);
    }

    public void deleteAllGameReviews(Long igdbId) {
        if(!gameRepository.existsById(igdbId)) throw new GameNotFoundException(igdbId);
        if(!reviewRepository.reviewExistsByIgdbId(igdbId)) throw new RuntimeException(String.format("No review exists for game with id %d", igdbId));
        reviewRepository.deleteAllGameReviews(igdbId);
    }

}
