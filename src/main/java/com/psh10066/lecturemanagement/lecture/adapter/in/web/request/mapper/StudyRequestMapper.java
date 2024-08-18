package com.psh10066.lecturemanagement.lecture.adapter.in.web.request.mapper;

import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.StudiesRequest;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.StudiesCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudyRequestMapper {

    StudyRequestMapper INSTANCE = Mappers.getMapper(StudyRequestMapper.class);

    StudiesCommand toCommand(StudiesRequest request);
}
