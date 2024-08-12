package com.psh10066.lecturemanagement.systemlog.adapter.out.persistence;

import com.psh10066.lecturemanagement.jpaclient.AuditingFields;
import com.psh10066.lecturemanagement.systemlog.domain.SystemLog;
import com.psh10066.lecturemanagement.systemlog.domain.SystemLogType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Comment("시스템 로그")
@Getter
@Entity(name = "system_log")
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SystemLogJpaEntity extends AuditingFields {

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

    public static SystemLogJpaEntity from(SystemLog systemLog) {
        return SystemLogMapper.INSTANCE.from(systemLog);
    }

    public SystemLog toModel() {
        return SystemLogMapper.INSTANCE.toModel(this);
    }
}
