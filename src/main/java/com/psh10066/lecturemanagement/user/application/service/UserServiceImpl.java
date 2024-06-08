package com.psh10066.lecturemanagement.user.application.service;

import com.psh10066.lecturemanagement.user.application.port.in.UserService;
import com.psh10066.lecturemanagement.user.application.port.out.UserRepository;
import com.psh10066.lecturemanagement.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다."));
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public void save(String username, String password) {
        userRepository.save(User.createUser(username, password));
    }
}
