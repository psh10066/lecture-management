package com.psh10066.lecturemanagement.systemlog.application.port.in;

import com.psh10066.lecturemanagement.systemlog.domain.SystemLog;

public interface SystemLogService {

    SystemLog createSuccessLog(CreateSuccessLogCommand command);

    SystemLog createErrorLog(CreateErrorLogCommand command);
}
