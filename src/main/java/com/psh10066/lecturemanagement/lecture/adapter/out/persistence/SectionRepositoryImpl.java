package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.section.SectionJpaEntity;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.section.SectionJpaRepository;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.out.SectionRepository;
import com.psh10066.lecturemanagement.lecture.application.port.out.StudyRepository;
import com.psh10066.lecturemanagement.lecture.domain.Section;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
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

    @Override
    public List<LectureInfoDTO.SectionDTO> findInfoByCurriculumIdIn(List<Long> curriculumIds) {
        List<SectionJpaEntity> sectionList = sectionJpaRepository.findAllByCurriculumIdIn(curriculumIds);

        List<Long> sectionIds = sectionList.stream()
            .map(SectionJpaEntity::getSectionId)
            .toList();
        List<LectureInfoDTO.StudyDTO> studyList = studyRepository.findAllBySectionIdIn(sectionIds);

        return sectionList.stream()
            .map(section -> new LectureInfoDTO.SectionDTO(
                section.getSectionId(),
                section.getCurriculumId(),
                section.getSectionName(),
                studyList.stream()
                    .filter(study -> study.sectionId().equals(section.getSectionId()))
                    .toList()
            ))
            .toList();
    }
}
