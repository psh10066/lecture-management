package com.psh10066.lecturemanagement.domain.user;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    User save(User user);
}
