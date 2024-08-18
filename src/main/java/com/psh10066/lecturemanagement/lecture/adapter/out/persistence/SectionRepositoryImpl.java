package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.section.SectionJpaEntity;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.section.SectionJpaRepository;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.out.SectionRepository;
import com.psh10066.lecturemanagement.lecture.application.port.out.StudyRepository;
import com.psh10066.lecturemanagement.lecture.domain.Section;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SectionRepositoryImpl implements SectionRepository {

    private final SectionJpaRepository sectionJpaRepository;
    private final StudyRepository studyRepository;

    @Override
    public void register(Long curriculumId, RegisterLectureCommand.SectionDTO sectionDTO) {
        final Section section = sectionJpaRepository.save(SectionJpaEntity.from(
            Section.createSection(sectionDTO.sectionName(), curriculumId))
        ).toModel();

        sectionDTO.studyList().forEach(studyDTO ->
            studyRepository.register(section.sectionId(), studyDTO)
        );
    }
}
