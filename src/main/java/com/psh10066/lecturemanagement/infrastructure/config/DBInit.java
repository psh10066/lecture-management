package com.psh10066.lecturemanagement.infrastructure.config;

import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class DBInit implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        userRepository.save(User.createUser("hjlee", "0201"));
    }
}
