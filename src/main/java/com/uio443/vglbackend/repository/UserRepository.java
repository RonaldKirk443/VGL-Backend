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
    @Query("DELETE FROM User u WHERE u.id = ?1")
    void deleteUserById(Long id);

    @Transactional
    @Query("SELECT u FROM User u WHERE u.id = ?1")
    Optional<User> findUserById(Long id);

    @Transactional
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findUserByUsername(String username);

    @Transactional
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);

    @Transactional
    @Query("SELECT EXISTS(SELECT u FROM User  u WHERE u.username = ?1)")
    boolean existsByUsername(String username);

    @Transactional
    @Query("SELECT EXISTS(SELECT u FROM User  u WHERE u.email = ?1)")
    boolean existsByEmail(String email);

}
