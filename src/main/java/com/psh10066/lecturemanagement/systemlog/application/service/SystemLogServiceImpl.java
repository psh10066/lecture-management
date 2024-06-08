package com.psh10066.lecturemanagement.systemlog.application.service;

import com.psh10066.lecturemanagement.systemlog.application.port.in.CreateErrorLogCommand;
import com.psh10066.lecturemanagement.systemlog.application.port.in.CreateSuccessLogCommand;
import com.psh10066.lecturemanagement.systemlog.application.port.in.SystemLogService;
import com.psh10066.lecturemanagement.systemlog.application.port.out.SystemLogRepository;
import com.psh10066.lecturemanagement.systemlog.domain.SystemLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemLogServiceImpl implements SystemLogService {

    private final SystemLogRepository systemLogRepository;

    @Override
    public SystemLog createSuccessLog(CreateSuccessLogCommand command) {
        return systemLogRepository.save(command.toDomain());
    }

    @Override
    public SystemLog createErrorLog(CreateErrorLogCommand command) {
        return systemLogRepository.save(command.toDomain());
    }
}
