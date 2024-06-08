package com.psh10066.lecturemanagement.domain.section;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SectionRepositoryImpl implements SectionRepository {

    private final SectionJpaRepository sectionJpaRepository;

    @Override
    public Section save(Section section) {
        return sectionJpaRepository.save(section);
    }
}
