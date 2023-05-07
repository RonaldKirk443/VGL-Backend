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

    @PostMapping("/add/{userId}/{igdbId}")
    public ResponseEntity<String> addReview(@PathVariable(value = "userId") Long userId, @PathVariable(value = "igdbId") Long igdbId, @RequestBody String review) {
        reviewService.addReview(userId, igdbId, review);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @PutMapping("/game/{igdbId}/user/{userId}/updateReview")
    public ResponseEntity<String> updateReview(@PathVariable(value = "userId") Long userId, @PathVariable(value = "igdbId") Long igdbId, @RequestBody String review) {
        reviewService.updateReview(userId, igdbId, review);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/get/game/{igdbId}/user/{userId}")
    public ResponseEntity<Object> getReviewByUserIdIgdbId(@PathVariable(value = "userId") Long userId, @PathVariable(value = "igdbId") Long igdbId) {
        Object review = reviewService.getReview(userId, igdbId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/get/game/{igdbId}/reviews")
    public ResponseEntity<List<Object>> getReviewsbyigdbId(@PathVariable(value = "igdbId") Long igdbId) {
        List<Object> reviewList = reviewService.getReviewListByGame(igdbId);
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }

    @GetMapping("/get/user/{userId}/reviews")
    public ResponseEntity<List<Object>> getReviewsbyuserId(@PathVariable(value = "userId") Long userId) {
        List<Object> reviewList = reviewService.getReviewListByUser(userId);
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }

    @DeleteMapping("/game/{igdbId}/user/{userId}/delete")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "userId") Long userId, @PathVariable(value = "igdbId") Long igdbId) {
        reviewService.deleteReview(userId, igdbId);
        return new ResponseEntity<>(String.format("Review with user id %d was removed from game with id %d", userId, igdbId), HttpStatus.OK);
    }

    @DeleteMapping("game/{igdbId}/deleteAllReviews")
    public ResponseEntity<String> deleteAllGameReviews(@PathVariable(value = "igdbId") Long igdbId) {
        reviewService.deleteAllGameReviews(igdbId);
        return new ResponseEntity<>(String.format("All Reviews of game with id %d were removed", igdbId), HttpStatus.OK);
    }

}
