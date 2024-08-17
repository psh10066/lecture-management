package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecturer;

import com.psh10066.lecturemanagement.lecture.domain.Lecturer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LecturerMapper {

    LecturerMapper INSTANCE = Mappers.getMapper(LecturerMapper.class);

    Lecturer toModel(LecturerJpaEntity lecturerJpaEntity);

    LecturerJpaEntity from(Lecturer lecturer);
}
