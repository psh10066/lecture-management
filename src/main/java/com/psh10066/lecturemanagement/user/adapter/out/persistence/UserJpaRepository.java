package com.psh10066.lecturemanagement.user.adapter.out.persistence;

import com.psh10066.lecturemanagement.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
