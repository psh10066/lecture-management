package com.psh10066.lecturemanagement.systemlog.application.port.out;

import com.psh10066.lecturemanagement.systemlog.domain.SystemLog;

public interface SystemLogRepository {

    SystemLog save(SystemLog systemLog);
}
