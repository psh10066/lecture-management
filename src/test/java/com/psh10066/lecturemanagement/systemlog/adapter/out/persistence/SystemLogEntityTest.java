package com.psh10066.lecturemanagement.systemlog.adapter.out.persistence;

import com.psh10066.lecturemanagement.systemlog.domain.SystemLog;
import com.psh10066.lecturemanagement.systemlog.domain.SystemLogType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SystemLogEntityTest {

    @Test
    @DisplayName("도메인 엔티티에서 영속성 엔티티로 변환할 수 있다.")
    void from() {
        // given
        SystemLog systemLog = SystemLog.builder()
            .systemLogId(1L)
            .systemLogType(SystemLogType.SUCCESS)
            .userId(2L)
            .httpMethod("GET")
            .requestURL("http://localhost:8080/test")
            .requestParameters("{\"requestParam1\": \"param1\"}")
            .requestHeaders("{\"requestHeader1\": \"request1\"}")
            .responseHeaders("{\"responseHeader1\": \"response1\"}")
            .responseBody("{\"responseBody1\": \"body1\"}")
            .errorMessage("Runtime Error!!")
            .createdAt(LocalDateTime.of(2023, 1, 2, 12, 34, 56))
            .updatedAt(LocalDateTime.of(2024, 2, 3, 5, 13, 43))
            .build();

        // when
        SystemLogEntity systemLogEntity = SystemLogEntity.from(systemLog);

        // then
        assertAll(
            () -> assertThat(systemLogEntity.getSystemLogId()).isEqualTo(1),
            () -> assertThat(systemLogEntity.getSystemLogType()).isEqualTo(SystemLogType.SUCCESS),
            () -> assertThat(systemLogEntity.getUserId()).isEqualTo(2),
            () -> assertThat(systemLogEntity.getHttpMethod()).isEqualTo("GET"),
            () -> assertThat(systemLogEntity.getRequestURL()).isEqualTo("http://localhost:8080/test"),
            () -> assertThat(systemLogEntity.getRequestParameters()).isEqualTo("{\"requestParam1\": \"param1\"}"),
            () -> assertThat(systemLogEntity.getRequestHeaders()).isEqualTo("{\"requestHeader1\": \"request1\"}"),
            () -> assertThat(systemLogEntity.getResponseHeaders()).isEqualTo("{\"responseHeader1\": \"response1\"}"),
            () -> assertThat(systemLogEntity.getResponseBody()).isEqualTo("{\"responseBody1\": \"body1\"}"),
            () -> assertThat(systemLogEntity.getErrorMessage()).isEqualTo("Runtime Error!!"),
            () -> assertThat(systemLogEntity.getCreatedAt()).isEqualTo(LocalDateTime.of(2023, 1, 2, 12, 34, 56)),
            () -> assertThat(systemLogEntity.getUpdatedAt()).isEqualTo(LocalDateTime.of(2024, 2, 3, 5, 13, 43))
        );
    }

    @Test
    @DisplayName("영속성 엔티티에서 도메인 엔티티로 변환할 수 있다.")
    void toDomain() {
        // given
        SystemLogEntity systemLogEntity = SystemLogEntity.builder()
            .systemLogId(1L)
            .systemLogType(SystemLogType.SUCCESS)
            .userId(2L)
            .httpMethod("GET")
            .requestURL("http://localhost:8080/test")
            .requestParameters("{\"requestParam1\": \"param1\"}")
            .requestHeaders("{\"requestHeader1\": \"request1\"}")
            .responseHeaders("{\"responseHeader1\": \"response1\"}")
            .responseBody("{\"responseBody1\": \"body1\"}")
            .errorMessage("Runtime Error!!")
            .createdAt(LocalDateTime.of(2023, 1, 2, 12, 34, 56))
            .updatedAt(LocalDateTime.of(2024, 2, 3, 5, 13, 43))
            .build();

        // when
        SystemLog systemLog = systemLogEntity.toDomain();

        // then
        assertAll(
            () -> assertThat(systemLog.getSystemLogId()).isEqualTo(1),
            () -> assertThat(systemLog.getSystemLogType()).isEqualTo(SystemLogType.SUCCESS),
            () -> assertThat(systemLog.getUserId()).isEqualTo(2),
            () -> assertThat(systemLog.getHttpMethod()).isEqualTo("GET"),
            () -> assertThat(systemLog.getRequestURL()).isEqualTo("http://localhost:8080/test"),
            () -> assertThat(systemLog.getRequestParameters()).isEqualTo("{\"requestParam1\": \"param1\"}"),
            () -> assertThat(systemLog.getRequestHeaders()).isEqualTo("{\"requestHeader1\": \"request1\"}"),
            () -> assertThat(systemLog.getResponseHeaders()).isEqualTo("{\"responseHeader1\": \"response1\"}"),
            () -> assertThat(systemLog.getResponseBody()).isEqualTo("{\"responseBody1\": \"body1\"}"),
            () -> assertThat(systemLog.getErrorMessage()).isEqualTo("Runtime Error!!"),
            () -> assertThat(systemLog.getCreatedAt()).isEqualTo(LocalDateTime.of(2023, 1, 2, 12, 34, 56)),
            () -> assertThat(systemLog.getUpdatedAt()).isEqualTo(LocalDateTime.of(2024, 2, 3, 5, 13, 43))
        );
    }
}