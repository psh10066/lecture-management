package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.application.port.out.SectionRepository;
import com.psh10066.lecturemanagement.lecture.domain.Section;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SectionRepositoryImpl implements SectionRepository {

    private final SectionJpaRepository sectionJpaRepository;

    @Override
    public Section save(Section section) {
        return sectionJpaRepository.save(SectionJpaEntity.from(section)).toModel();
    }
}
