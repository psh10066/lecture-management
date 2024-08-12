package com.psh10066.lecturemanagement.user.adapter.out.persistence;

import com.psh10066.lecturemanagement.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserJpaEntityTest {

    @Test
    @DisplayName("도메인 엔티티에서 영속성 엔티티로 변환할 수 있다.")
    void from() {
        // given
        User user = User.builder()
            .userId(1L)
            .username("username")
            .password("password")
            .enabled(true)
            .createdAt(LocalDateTime.of(2023, 1, 2, 12, 34, 56))
            .updatedAt(LocalDateTime.of(2024, 2, 3, 5, 13, 43))
            .build();

        // when
        UserJpaEntity userJpaEntity = UserJpaEntity.from(user);

        // then
        assertAll(
            () -> assertThat(userJpaEntity.getUserId()).isEqualTo(1L),
            () -> assertThat(userJpaEntity.getUsername()).isEqualTo("username"),
            () -> assertThat(userJpaEntity.getPassword()).isEqualTo("password"),
            () -> assertThat(userJpaEntity.isEnabled()).isTrue(),
            () -> assertThat(userJpaEntity.getCreatedAt()).isEqualTo(LocalDateTime.of(2023, 1, 2, 12, 34, 56)),
            () -> assertThat(userJpaEntity.getUpdatedAt()).isEqualTo(LocalDateTime.of(2024, 2, 3, 5, 13, 43))
        );
    }

    @Test
    @DisplayName("영속성 엔티티에서 도메인 엔티티로 변환할 수 있다.")
    void toModel() {
        // given
        UserJpaEntity userJpaEntity = UserJpaEntity.builder()
            .userId(1L)
            .username("username")
            .password("password")
            .enabled(true)
            .createdAt(LocalDateTime.of(2023, 1, 2, 12, 34, 56))
            .updatedAt(LocalDateTime.of(2024, 2, 3, 5, 13, 43))
            .build();

        // when
        User user = userJpaEntity.toModel();

        // then
        assertAll(
            () -> assertThat(user.getUserId()).isEqualTo(1L),
            () -> assertThat(user.getUsername()).isEqualTo("username"),
            () -> assertThat(user.getPassword()).isEqualTo("password"),
            () -> assertThat(user.isEnabled()).isTrue(),
            () -> assertThat(user.getCreatedAt()).isEqualTo(LocalDateTime.of(2023, 1, 2, 12, 34, 56)),
            () -> assertThat(user.getUpdatedAt()).isEqualTo(LocalDateTime.of(2024, 2, 3, 5, 13, 43))
        );
    }
}