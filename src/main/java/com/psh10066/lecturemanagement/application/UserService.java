package com.psh10066.lecturemanagement.application;

public interface UserService {

    boolean existsByUsername(String username);

    void save(String username, String password);
}
