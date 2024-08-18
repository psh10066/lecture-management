package com.psh10066.lecturemanagement.lecture.adapter.in.web.request.mapper;

import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.LecturesRequest;
import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.ModifyLectureRequest;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.LecturesCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.ModifyLectureCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LectureRequestMapper {

    LectureRequestMapper INSTANCE = Mappers.getMapper(LectureRequestMapper.class);

    LecturesCommand toCommand(LecturesRequest request);

    ModifyLectureCommand toCommand(Long lectureId, ModifyLectureRequest request);
}
