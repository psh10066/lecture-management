package com.psh10066.lecturemanagement.domain.systemlog;

import com.psh10066.lecturemanagement.domain.common.AuditingFields;
import com.psh10066.lecturemanagement.domain.systemlog.type.SystemLogType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("시스템 로그")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SystemLog extends AuditingFields {

    @Comment("시스템 로그 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long systemLogId;

    @Comment("시스템 로그 유형")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SystemLogType systemLogType;

    @Comment("사용자 고유번호")
    private Long userId;

    @Comment("Http Method")
    @Column(length = 20, nullable = false)
    private String httpMethod;

    @Comment("Request URL")
    @Column(length = 4096, nullable = false)
    private String requestURL;

    @Comment("Request Parameters")
    @Column(columnDefinition = "TEXT")
    private String requestParameters;

    @Comment("Request Headers")
    @Column(columnDefinition = "TEXT")
    private String requestHeaders;

    @Comment("Response Headers")
    @Column(columnDefinition = "TEXT")
    private String responseHeaders;

    @Comment("Response Body")
    @Column(columnDefinition = "TEXT")
    private String responseBody;

    @Comment("Error Message")
    @Column(length = 4096)
    private String errorMessage;

    public static SystemLog createSuccessLog(Long userId, String httpMethod, String requestURL, String requestParameters, String requestHeaders, String responseHeaders, String responseBody) {
        return new SystemLog(SystemLogType.SUCCESS, userId, httpMethod, requestURL, requestParameters, requestHeaders, responseHeaders, responseBody, null);
    }

    public static SystemLog createErrorLog(Long userId, String httpMethod, String requestURL, String requestParameters, String requestHeaders, String responseHeaders, String errorMessage) {
        return new SystemLog(SystemLogType.ERROR, userId, httpMethod, requestURL, requestParameters, requestHeaders, responseHeaders, null, errorMessage);
    }

    private SystemLog(SystemLogType systemLogType, Long userId, String httpMethod, String requestURL, String requestParameters, String requestHeaders, String responseHeaders, String responseBody, String errorMessage) {
        this.systemLogType = systemLogType;
        this.userId = userId;
        this.httpMethod = httpMethod;
        this.requestURL = requestURL;
        this.requestParameters = requestParameters;
        this.requestHeaders = requestHeaders;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
        this.errorMessage = errorMessage;
    }
}
