package com.psh10066.lecturemanagement.systemlog.adapter.out.persistence;

import com.psh10066.lecturemanagement.systemlog.domain.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemLogJpaRepository extends JpaRepository<SystemLog, Long> {
}
