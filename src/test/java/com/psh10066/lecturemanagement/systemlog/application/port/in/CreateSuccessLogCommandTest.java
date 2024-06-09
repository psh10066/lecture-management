package com.psh10066.lecturemanagement.systemlog.application.port.in;

import com.psh10066.lecturemanagement.helper.CustomTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CreateSuccessLogCommandTest {

    @Test
    @DisplayName("httpMethod 필드는 null일 수 없다.")
    void test1() {
        // given
        // when
        // then
        CustomTestHelper.assertBeanValidation(
            "httpMethod",
            () -> new CreateSuccessLogCommand(
                1L,
                null,
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                "{\"responseBody1\": \"body1\"}"
            )
        );
    }

    @Test
    @DisplayName("httpMethod 필드는 비어있을 수 없다.")
    void test2() {
        // given
        // when
        // then
        CustomTestHelper.assertBeanValidation(
            "httpMethod",
            () -> new CreateSuccessLogCommand(
                1L,
                " ",
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                "{\"responseBody1\": \"body1\"}"
            )
        );
    }

    @Test
    @DisplayName("requestURL 필드는 null일 수 없다.")
    void test3() {
        // given
        // when
        // then
        CustomTestHelper.assertBeanValidation(
            "requestURL",
            () -> new CreateSuccessLogCommand(
                1L,
                "GET",
                null,
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                "{\"responseBody1\": \"body1\"}"
            )
        );
    }

    @Test
    @DisplayName("requestURL 필드는 비어있을 수 없다.")
    void test4() {
        // given
        // when
        // then
        CustomTestHelper.assertBeanValidation(
            "requestURL",
            () -> new CreateSuccessLogCommand(
                1L,
                "GET",
                " ",
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                "{\"responseBody1\": \"body1\"}"
            )
        );
    }

    @Test
    @DisplayName("requestParameters 필드는 null일 수 없다.")
    void test5() {
        // given
        // when
        // then
        CustomTestHelper.assertBeanValidation(
            "requestParameters",
            () -> new CreateSuccessLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                null,
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                "{\"responseBody1\": \"body1\"}"
            )
        );
    }

    @Test
    @DisplayName("requestParameters 필드는 비어있을 수 없다.")
    void test6() {
        // given
        // when
        // then
        CustomTestHelper.assertBeanValidation(
            "requestParameters",
            () -> new CreateSuccessLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                " ",
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                "{\"responseBody1\": \"body1\"}"
            )
        );
    }

    @Test
    @DisplayName("requestHeaders 필드는 null일 수 없다.")
    void test7() {
        // given
        // when
        // then
        CustomTestHelper.assertBeanValidation(
            "requestHeaders",
            () -> new CreateSuccessLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                null,
                "{\"responseHeader1\": \"response1\"}",
                "{\"responseBody1\": \"body1\"}"
            )
        );
    }

    @Test
    @DisplayName("requestHeaders 필드는 비어있을 수 없다.")
    void test8() {
        // given
        // when
        // then
        CustomTestHelper.assertBeanValidation(
            "requestHeaders",
            () -> new CreateSuccessLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                " ",
                "{\"responseHeader1\": \"response1\"}",
                "{\"responseBody1\": \"body1\"}"
            )
        );
    }

    @Test
    @DisplayName("responseHeaders 필드는 null일 수 없다.")
    void test9() {
        // given
        // when
        // then
        CustomTestHelper.assertBeanValidation(
            "responseHeaders",
            () -> new CreateSuccessLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                null,
                "{\"responseBody1\": \"body1\"}"
            )
        );
    }

    @Test
    @DisplayName("responseHeaders 필드는 비어있을 수 없다.")
    void test10() {
        // given
        // when
        // then
        CustomTestHelper.assertBeanValidation(
            "responseHeaders",
            () -> new CreateSuccessLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                " ",
                "{\"responseBody1\": \"body1\"}"
            )
        );
    }
}