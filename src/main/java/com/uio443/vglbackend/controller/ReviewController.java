package com.uio443.vglbackend.controller;

import com.uio443.vglbackend.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews/game/{igdbId}")
    public ResponseEntity<List<Object>> getReviewsByIgdbId(@PathVariable(value = "igdbId") Long igdbId) {
        List<Object> reviewList = reviewService.getReviewListByGame(igdbId);
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }

    @GetMapping("/reviews/user/{userId}")
    public ResponseEntity<List<Object>> getReviewsByUserId(@PathVariable(value = "userId") Long userId) {
        List<Object> reviewList = reviewService.getReviewListByUser(userId);
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }

    @GetMapping("/comment/{userId}/{igdbId}")
    public ResponseEntity<String> getReviewCommentByUserIdIgdbId(@PathVariable(value = "userId") Long userId, @PathVariable(value = "igdbId") Long igdbId) {
        String review = reviewService.getReviewComment(userId, igdbId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/rating/{userId}/{igdbId}")
    public ResponseEntity<Double> getRatingByUserIdIgdbId(@PathVariable(value = "userId") Long userId, @PathVariable(value = "igdbId") Long igdbId) {
        double review = reviewService.getUserRating(userId, igdbId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/rating/{igdbId}")
    public ResponseEntity<Double> getAverageRatingByIgdbId(@PathVariable(value = "igdbId") Long igdbId) {
        double review = reviewService.getGameRating(igdbId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/addComment/{userId}/{igdbId}")
    public ResponseEntity<String> addReviewComment(@PathVariable(value = "userId") Long userId, @PathVariable(value = "igdbId") Long igdbId, @RequestBody String review) {
        reviewService.addReviewComment(userId, igdbId, review);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @PostMapping("/addRating/{userId}/{igdbId}/{rating}")
    public ResponseEntity<String> addRating(@PathVariable(value = "userId") Long userId, @PathVariable(value = "igdbId") Long igdbId, @PathVariable(value = "rating") Double rating) {
        reviewService.addRating(userId, igdbId, rating);
        return new ResponseEntity<>(String.format("User with id %d has rated game with id %d as %f/10", userId, igdbId, rating), HttpStatus.CREATED);
    }

    @PutMapping("/updateComment/{userId}/{igdbId}")
    public ResponseEntity<String> updateReviewComment(@PathVariable(value = "userId") Long userId, @PathVariable(value = "igdbId") Long igdbId, @RequestBody String review) {
        reviewService.updateReviewComment(userId, igdbId, review);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/updateRating/{userId}/{igdbId}/{rating}")
    public ResponseEntity<String> updateRating(@PathVariable(value = "userId") Long userId, @PathVariable(value = "igdbId") Long igdbId, @PathVariable(value = "rating") Double rating) {
        reviewService.updateRating(userId, igdbId, rating);
        return new ResponseEntity<>(String.format("User with id %d has update his rating for game with id %d as %f/10", userId, igdbId, rating), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}/{igdbId}")
    public ResponseEntity<String> deleteReviewComment(@PathVariable(value = "userId") Long userId, @PathVariable(value = "igdbId") Long igdbId) {
        reviewService.deleteReviewComment(userId, igdbId);
        return new ResponseEntity<>(String.format("Review with user id %d was removed from game with id %d", userId, igdbId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllReviews/game/{igdbId}")
    public ResponseEntity<String> deleteAllGameReviewComments(@PathVariable(value = "igdbId") Long igdbId) {
        reviewService.deleteAllGameReviewComments(igdbId);
        return new ResponseEntity<>(String.format("All Reviews of game with id %d were removed", igdbId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllReviews/user/{userId}")
    public ResponseEntity<String> deleteAllUserReviewComments(@PathVariable(value = "userId") Long userId) {
        reviewService.deleteAllUserReviewComments(userId);
        return new ResponseEntity<>(String.format("All Reviews of user with id %d were removed", userId), HttpStatus.OK);
    }

}
