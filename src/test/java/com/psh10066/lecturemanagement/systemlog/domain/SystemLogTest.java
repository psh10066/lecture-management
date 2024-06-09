package com.psh10066.lecturemanagement.systemlog.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SystemLogTest {

    @Test
    @DisplayName("성공 로그를 생성할 수 있다.")
    void createSuccessLog() {
        // given
        // when
        SystemLog systemLog = SystemLog.createSuccessLog(1L,
            "GET",
            "http://localhost:8080/test",
            "{\"requestParam1\": \"param1\"}",
            "{\"requestHeader1\": \"request1\"}",
            "{\"responseHeader1\": \"response1\"}",
            "{\"responseBody1\": \"body1\"}"
        );

        // then
        assertAll(
            () -> assertThat(systemLog.getSystemLogType()).isEqualTo(SystemLogType.SUCCESS),
            () -> assertThat(systemLog.getUserId()).isEqualTo(1),
            () -> assertThat(systemLog.getHttpMethod()).isEqualTo("GET"),
            () -> assertThat(systemLog.getRequestURL()).isEqualTo("http://localhost:8080/test"),
            () -> assertThat(systemLog.getRequestParameters()).isEqualTo("{\"requestParam1\": \"param1\"}"),
            () -> assertThat(systemLog.getRequestHeaders()).isEqualTo("{\"requestHeader1\": \"request1\"}"),
            () -> assertThat(systemLog.getResponseHeaders()).isEqualTo("{\"responseHeader1\": \"response1\"}"),
            () -> assertThat(systemLog.getResponseBody()).isEqualTo("{\"responseBody1\": \"body1\"}"),
            () -> assertThat(systemLog.getErrorMessage()).isNull()
        );
    }

    @Test
    @DisplayName("실패 로그를 생성할 수 있다.")
    void createErrorLog() {
        // given
        // when
        SystemLog systemLog = SystemLog.createErrorLog(1L,
            "GET",
            "http://localhost:8080/test",
            "{\"requestParam1\": \"param1\"}",
            "{\"requestHeader1\": \"request1\"}",
            "{\"responseHeader1\": \"response1\"}",
            "Runtime Error!!"
        );

        // then
        assertAll(
            () -> assertThat(systemLog.getSystemLogType()).isEqualTo(SystemLogType.ERROR),
            () -> assertThat(systemLog.getUserId()).isEqualTo(1),
            () -> assertThat(systemLog.getHttpMethod()).isEqualTo("GET"),
            () -> assertThat(systemLog.getRequestURL()).isEqualTo("http://localhost:8080/test"),
            () -> assertThat(systemLog.getRequestParameters()).isEqualTo("{\"requestParam1\": \"param1\"}"),
            () -> assertThat(systemLog.getRequestHeaders()).isEqualTo("{\"requestHeader1\": \"request1\"}"),
            () -> assertThat(systemLog.getResponseHeaders()).isEqualTo("{\"responseHeader1\": \"response1\"}"),
            () -> assertThat(systemLog.getErrorMessage()).isEqualTo("Runtime Error!!"),
            () -> assertThat(systemLog.getResponseBody()).isNull()
        );
    }
}