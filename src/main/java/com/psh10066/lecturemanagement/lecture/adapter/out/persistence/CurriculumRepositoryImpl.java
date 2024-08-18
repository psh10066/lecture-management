package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum.CurriculumJpaEntity;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum.CurriculumJpaRepository;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecturetocurriculum.LectureToCurriculumJpaEntity;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecturetocurriculum.LectureToCurriculumJpaRepository;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.ModifyLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.out.CurriculumRepository;
import com.psh10066.lecturemanagement.lecture.application.port.out.LecturerRepository;
import com.psh10066.lecturemanagement.lecture.application.port.out.SectionRepository;
import com.psh10066.lecturemanagement.lecture.domain.Curriculum;
import com.psh10066.lecturemanagement.lecture.domain.Lecturer;
import com.psh10066.lecturemanagement.user.domain.User;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional
@RequiredArgsConstructor
public class CurriculumRepositoryImpl implements CurriculumRepository {

    private final CurriculumJpaRepository curriculumJpaRepository;
    private final LecturerRepository lecturerRepository;
    private final LectureToCurriculumJpaRepository lectureToCurriculumJpaRepository;
    private final SectionRepository sectionRepository;

    @Override
    public Curriculum save(Curriculum curriculum) {
        return curriculumJpaRepository.save(CurriculumJpaEntity.from(curriculum)).toModel();
    }

    @Override
    public void updateLecturer(ModifyLectureCommand command, User user) {
        Map<Long, String> lecturerMap = command.curriculumList().stream()
            .collect(Collectors.toMap(
                ModifyLectureCommand.InnerDTO::curriculumId,
                ModifyLectureCommand.InnerDTO::lecturerName
            ));
        Set<Long> curriculumIds = lecturerMap.keySet();
        List<Curriculum> curriculumJpaEntityList = curriculumJpaRepository.findAllById(curriculumIds).stream()
            .map(CurriculumJpaEntity::toModel)
            .toList();

        curriculumJpaEntityList.forEach(curriculum -> {
            String newLecturerName = lecturerMap.get(curriculum.curriculumId());
            if (curriculum.lecturerId() == null || !lecturerRepository.getById(curriculum.lecturerId()).lecturerName().equals(newLecturerName)) {
                Curriculum newCurriculum = curriculum.updateCurriculum(lecturerRepository.save(Lecturer.createLecturer(newLecturerName, user.getUserId())).lecturerId());
                curriculumJpaRepository.save(CurriculumJpaEntity.from(newCurriculum));
            }
        });
    }

    @Override
    public Curriculum register(User user, Long lectureId, RegisterLectureCommand.CurriculumDTO curriculumDTO) {
        Long lecturerId = null;
        if (StringUtils.isNotBlank(curriculumDTO.lecturerName())) {
            lecturerId = lecturerRepository.findByLecturerNameAndUser(curriculumDTO.lecturerName(), user)
                .orElseGet(() -> lecturerRepository.save(Lecturer.createLecturer(curriculumDTO.lecturerName(), user.getUserId())))
                .lecturerId();
        }

        final Curriculum curriculum = curriculumJpaRepository.save(CurriculumJpaEntity.from(
            Curriculum.createCurriculum(curriculumDTO.curriculumName(), lecturerId))
        ).toModel();

        lectureToCurriculumJpaRepository.save(new LectureToCurriculumJpaEntity(lectureId, curriculum.curriculumId()));

        curriculumDTO.sectionList().forEach(sectionDTO ->
            sectionRepository.register(curriculum.curriculumId(), sectionDTO)
        );

        return curriculum;
    }
}
