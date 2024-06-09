package com.psh10066.lecturemanagement.mock;

import com.psh10066.lecturemanagement.systemlog.application.port.out.SystemLogRepository;
import com.psh10066.lecturemanagement.systemlog.domain.SystemLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FakeSystemLogRepository implements SystemLogRepository {

    private long autoIncrementId = 1;
    private final List<SystemLog> data = new ArrayList<>();

    @Override
    public SystemLog save(SystemLog systemLog) {
        if (systemLog.getSystemLogId() == null || systemLog.getSystemLogId() == 0) {
            SystemLog newSystemLog = SystemLog.builder()
                .systemLogId(autoIncrementId++)
                .systemLogType(systemLog.getSystemLogType())
                .userId(systemLog.getUserId())
                .httpMethod(systemLog.getHttpMethod())
                .requestURL(systemLog.getRequestURL())
                .requestParameters(systemLog.getRequestParameters())
                .requestHeaders(systemLog.getRequestHeaders())
                .responseHeaders(systemLog.getResponseHeaders())
                .responseBody(systemLog.getResponseBody())
                .errorMessage(systemLog.getErrorMessage())
                .createdAt(systemLog.getCreatedAt())
                .updatedAt(systemLog.getUpdatedAt())
                .build();
            data.add(newSystemLog);
            return newSystemLog;
        } else {
            data.removeIf(item -> Objects.equals(item.getSystemLogId(), systemLog.getSystemLogId()));
            data.add(systemLog);
            return systemLog;
        }
    }
}
