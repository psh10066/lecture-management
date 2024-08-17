package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum;

import com.psh10066.lecturemanagement.lecture.domain.Curriculum;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurriculumMapper {

    CurriculumMapper INSTANCE = Mappers.getMapper(CurriculumMapper.class);

    Curriculum toModel(CurriculumJpaEntity sectionJpaEntity);

    CurriculumJpaEntity from(Curriculum curriculum);
}
