package com.psh10066.lecturemanagement.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserTest {

    @Test
    @DisplayName("사용자를 생성할 수 있다.")
    void createUser() {
        // given
        // when
        User user = User.createUser("username", "password");

        // then
        assertThat(user.getUsername()).isEqualTo("username");
        assertThat(user.getPassword()).isEqualTo("password");
        assertThat(user.isEnabled()).isTrue();
    }
}