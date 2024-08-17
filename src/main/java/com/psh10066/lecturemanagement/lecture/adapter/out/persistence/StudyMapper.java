package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.domain.Study;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudyMapper {

    StudyMapper INSTANCE = Mappers.getMapper(StudyMapper.class);

    Study toModel(StudyJpaEntity studyJpaEntity);

    StudyJpaEntity from(Study study);
}
