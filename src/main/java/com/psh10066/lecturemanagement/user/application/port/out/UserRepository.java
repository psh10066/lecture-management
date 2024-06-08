package com.psh10066.lecturemanagement.user.application.port.out;

import com.psh10066.lecturemanagement.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    User save(User user);
}
