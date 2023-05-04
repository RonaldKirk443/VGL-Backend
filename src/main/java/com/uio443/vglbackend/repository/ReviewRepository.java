package com.uio443.vglbackend.repository;

import com.uio443.vglbackend.model.Game;
import com.uio443.vglbackend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Game> {


    @Transactional
    @Modifying
    @Query("DELETE FROM Review e WHERE e.user.id = ?1 AND e.game.igdbId = ?2")
    void deleteReview(Long userId, Long igdbId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Review e WHERE e.game.igdbId = ?1")
    void deleteAllGameReviews(Long igdbId);

    @Transactional
    @Query("SELECT e FROM Review e WHERE e.user.id = ?1")
    List<Review> getAllReviewsByUserId(Long userId);

    @Transactional
    @Query("SELECT e FROM Review e WHERE e.game.igdbId = ?1")
    List<Review> getAllReviewsByigdbId(Long igdbId);

    @Transactional
    @Query("SELECT e FROM Review e WHERE e.user.id = ?1 AND e.game.igdbId = ?2")
    Optional<Review> getReviewByUserIdIgdbId(Long userId, Long igdbId);

    @Transactional
    @Query("SELECT EXISTS(SELECT e FROM Review e WHERE e.user.id = ?1 AND e.game.igdbId = ?2)")
    boolean reviewExistsByUserIdIgdbId(Long userId, Long igdbId);

    @Transactional
    @Query("SELECT EXISTS(SELECT e FROM Review e WHERE e.game.igdbId = ?1)")
    boolean reviewExistsByIgdbId(Long igdbId);


}
