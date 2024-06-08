package com.psh10066.lecturemanagement.domain.systemlog;

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
