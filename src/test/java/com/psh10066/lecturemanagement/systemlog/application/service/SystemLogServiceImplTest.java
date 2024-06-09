package com.psh10066.lecturemanagement.systemlog.application.service;

import com.psh10066.lecturemanagement.mock.FakeSystemLogRepository;
import com.psh10066.lecturemanagement.systemlog.application.port.in.CreateErrorLogCommand;
import com.psh10066.lecturemanagement.systemlog.application.port.in.CreateSuccessLogCommand;
import com.psh10066.lecturemanagement.systemlog.domain.SystemLog;
import com.psh10066.lecturemanagement.systemlog.domain.SystemLogType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SystemLogServiceImplTest {

    private SystemLogServiceImpl systemLogServiceImpl;

    @BeforeEach
    void setUp() {
        this.systemLogServiceImpl = new SystemLogServiceImpl(new FakeSystemLogRepository());
    }

    @Test
    @DisplayName("성공 로그를 저장할 수 있다.")
    void createSuccessLog() {
        // given
        CreateSuccessLogCommand command = new CreateSuccessLogCommand(
            1L,
            "GET",
            "http://localhost:8080/test",
            "{\"requestParam1\": \"param1\"}",
            "{\"requestHeader1\": \"request1\"}",
            "{\"responseHeader1\": \"response1\"}",
            "{\"responseBody1\": \"body1\"}"
        );

        // when
        SystemLog systemLog = systemLogServiceImpl.createSuccessLog(command);

        // then
        assertAll(
            () -> assertThat(systemLog.getSystemLogId()).isNotNull(),
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
    @DisplayName("실패 로그를 저장할 수 있다.")
    void createErrorLog() {
        // given
        CreateErrorLogCommand command = new CreateErrorLogCommand(
            1L,
            "GET",
            "http://localhost:8080/test",
            "{\"requestParam1\": \"param1\"}",
            "{\"requestHeader1\": \"request1\"}",
            "{\"responseHeader1\": \"response1\"}",
            "Runtime Error!!"
        );

        // when
        SystemLog systemLog = systemLogServiceImpl.createErrorLog(command);

        // then
        assertAll(
            () -> assertThat(systemLog.getSystemLogId()).isNotNull(),
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