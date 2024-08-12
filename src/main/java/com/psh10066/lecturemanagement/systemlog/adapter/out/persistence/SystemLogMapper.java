package com.psh10066.lecturemanagement.systemlog.adapter.out.persistence;

import com.psh10066.lecturemanagement.systemlog.domain.SystemLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemLogMapper {

    SystemLogMapper INSTANCE = Mappers.getMapper(SystemLogMapper.class);

    SystemLogJpaEntity from(SystemLog systemLog);

    SystemLog toModel(SystemLogJpaEntity systemLogJpaEntity);
}
