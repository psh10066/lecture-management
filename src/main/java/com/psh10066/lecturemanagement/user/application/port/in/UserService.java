package com.psh10066.lecturemanagement.user.application.port.in;

public interface UserService {

    boolean existsByUsername(String username);

    void save(String username, String password);
}
