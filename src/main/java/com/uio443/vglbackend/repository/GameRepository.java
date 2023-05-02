package com.uio443.vglbackend.repository;

import com.uio443.vglbackend.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Game e WHERE e.igdbID = ?1")
    void deleteGameByigdbID(Long igdbID);

    @Transactional
    @Query("SELECT e FROM Game e WHERE e.igdbID = ?1")
    Optional<Game> findGameByigdbID(Long igdbID);


}
