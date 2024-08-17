package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.section;

import com.psh10066.lecturemanagement.lecture.domain.Section;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SectionMapper {

    SectionMapper INSTANCE = Mappers.getMapper(SectionMapper.class);

    Section toModel(SectionJpaEntity sectionJpaEntity);

    SectionJpaEntity from(Section section);
}
