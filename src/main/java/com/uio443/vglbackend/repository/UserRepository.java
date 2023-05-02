package com.uio443.vglbackend.repository;

import com.uio443.vglbackend.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM User e WHERE e.id = ?1")
    void deleteUserById(Long id);

    @Transactional
    @Query("SELECT e FROM User e WHERE e.id = ?1")
    Optional<User> findUserById(Long id);

}
