package com.psh10066.lecturemanagement.systemlog.application.port.in;

import com.psh10066.lecturemanagement.helper.CustomTestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CreateErrorLogCommandTest {

    @Test
    @DisplayName("httpMethod 필드는 null일 수 없다.")
    void test1() {
        // given
        // when
        // then
        CustomTestHelper.assertBeanValidation(
            "httpMethod",
            () -> new CreateErrorLogCommand(
                1L,
                null,
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                "Runtime Error!!"
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
            () -> new CreateErrorLogCommand(
                1L,
                " ",
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                "Runtime Error!!"
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
            () -> new CreateErrorLogCommand(
                1L,
                "GET",
                null,
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                "Runtime Error!!"
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
            () -> new CreateErrorLogCommand(
                1L,
                "GET",
                " ",
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                "Runtime Error!!"
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
            () -> new CreateErrorLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                null,
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                "Runtime Error!!"
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
            () -> new CreateErrorLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                " ",
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                "Runtime Error!!"
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
            () -> new CreateErrorLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                null,
                "{\"responseHeader1\": \"response1\"}",
                "Runtime Error!!"
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
            () -> new CreateErrorLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                " ",
                "{\"responseHeader1\": \"response1\"}",
                "Runtime Error!!"
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
            () -> new CreateErrorLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                null,
                "Runtime Error!!"
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
            () -> new CreateErrorLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                " ",
                "Runtime Error!!"
            )
        );
    }

    @Test
    @DisplayName("errorMessage 필드는 null일 수 없다.")
    void test11() {
        // given
        // when
        // then
        CustomTestHelper.assertBeanValidation(
            "errorMessage",
            () -> new CreateErrorLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                null
            )
        );
    }

    @Test
    @DisplayName("errorMessage 필드는 비어있을 수 없다.")
    void test12() {
        // given
        // when
        // then
        CustomTestHelper.assertBeanValidation(
            "errorMessage",
            () -> new CreateErrorLogCommand(
                1L,
                "GET",
                "http://localhost:8080/test",
                "{\"requestParam1\": \"param1\"}",
                "{\"requestHeader1\": \"request1\"}",
                "{\"responseHeader1\": \"response1\"}",
                " "
            )
        );
    }
}