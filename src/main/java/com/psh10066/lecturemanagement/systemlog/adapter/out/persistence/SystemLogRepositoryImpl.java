package com.psh10066.lecturemanagement.systemlog.adapter.out.persistence;

import com.psh10066.lecturemanagement.systemlog.domain.SystemLog;
import com.psh10066.lecturemanagement.systemlog.application.port.out.SystemLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SystemLogRepositoryImpl implements SystemLogRepository {

    private final SystemLogJpaRepository systemLogJpaRepository;

    @Override
    public SystemLog save(SystemLog systemLog) {
        return systemLogJpaRepository.save(systemLog);
    }
}
