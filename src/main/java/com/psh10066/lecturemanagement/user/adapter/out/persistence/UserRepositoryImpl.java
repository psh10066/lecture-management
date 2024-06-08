package com.psh10066.lecturemanagement.user.adapter.out.persistence;

import com.psh10066.lecturemanagement.user.application.port.out.UserRepository;
import com.psh10066.lecturemanagement.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }
}
