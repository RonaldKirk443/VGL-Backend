package com.uio443.vglbackend.repository;

import com.uio443.vglbackend.model.User;
import com.uio443.vglbackend.model.UserGame;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserGameRepository extends JpaRepository<UserGame, Long> {

    @Transactional
    @Query("SELECT u FROM UserGame u WHERE u.user.id = ?1")
    List<UserGame> getAllUserGamesByUserId(Long userId);

    @Transactional
    @Query("SELECT u FROM UserGame u WHERE u.igdbId = ?1")
    List<UserGame> getAllUserGamesByIgdbId(Long igdbId);

    @Transactional
    @Query("SELECT u FROM UserGame u WHERE u.user.id = ?1 AND u.igdbId = ?2")
    Optional<UserGame> getGameByUserIdIgdbId(Long userId, Long igdbId);

    @Transactional
    @Query("SELECT EXISTS(SELECT u FROM UserGame u WHERE u.user.id = ?1 AND u.igdbId = ?2)")
    boolean existsByUserIdIgdbId(Long userId, Long igdbId);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserGame u WHERE u.user.id = ?1 AND u.igdbId = ?2")
    void deleteGame(Long userId, Long igdbId);

    @Transactional
    @Query("SELECT u.id FROM User u join u.userGameList l where l.igdbId=?1")
    List<Long> getAllUserIdsByIgdbId(Long igdbId);

    //Reviews
    @Transactional
    @Query("SELECT u.user.id, u.rating, u.review FROM UserGame u WHERE u.igdbId = ?1")
    List<Object> getAllReviewsByigdbId(Long igdbId);

    @Transactional
    @Query("SELECT u.igdbId, u.rating, u.review FROM UserGame u WHERE u.user.id = ?1")
    List<Object> getAllReviewsByUserId(Long userId);


}
