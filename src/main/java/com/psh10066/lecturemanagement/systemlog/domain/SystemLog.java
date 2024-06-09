package com.psh10066.lecturemanagement.systemlog.domain;

import com.psh10066.lecturemanagement.core.util.DateTimeFields;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class SystemLog extends DateTimeFields {

    private final Long systemLogId;
    private final SystemLogType systemLogType;
    private final Long userId;
    private final String httpMethod;
    private final String requestURL;
    private final String requestParameters;
    private final String requestHeaders;
    private final String responseHeaders;
    private final String responseBody;
    private final String errorMessage;

    public static SystemLog createSuccessLog(Long userId, String httpMethod, String requestURL, String requestParameters, String requestHeaders, String responseHeaders, String responseBody) {
        return SystemLog.builder()
            .systemLogType(SystemLogType.SUCCESS)
            .userId(userId)
            .httpMethod(httpMethod)
            .requestURL(requestURL)
            .requestParameters(requestParameters)
            .requestHeaders(requestHeaders)
            .responseHeaders(responseHeaders)
            .responseBody(responseBody)
            .build();
    }

    public static SystemLog createErrorLog(Long userId, String httpMethod, String requestURL, String requestParameters, String requestHeaders, String responseHeaders, String errorMessage) {
        return SystemLog.builder()
            .systemLogType(SystemLogType.ERROR)
            .userId(userId)
            .httpMethod(httpMethod)
            .requestURL(requestURL)
            .requestParameters(requestParameters)
            .requestHeaders(requestHeaders)
            .responseHeaders(responseHeaders)
            .errorMessage(errorMessage)
            .build();
    }
}
