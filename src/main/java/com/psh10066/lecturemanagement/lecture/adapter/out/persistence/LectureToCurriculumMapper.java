package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.domain.LectureToCurriculum;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LectureToCurriculumMapper {

    LectureToCurriculumMapper INSTANCE = Mappers.getMapper(LectureToCurriculumMapper.class);

    LectureToCurriculum toModel(LectureToCurriculumJpaEntity lectureToCurriculumJpaEntity);

    LectureToCurriculumJpaEntity from(LectureToCurriculum lectureToCurriculum);
}
