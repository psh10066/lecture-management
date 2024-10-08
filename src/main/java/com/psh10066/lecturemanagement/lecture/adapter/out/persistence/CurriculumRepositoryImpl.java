package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum.CurriculumJpaEntity;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum.CurriculumJpaRepository;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum.dto.CurriculumDetailDTO;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecturetocurriculum.LectureToCurriculumJpaEntity;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecturetocurriculum.LectureToCurriculumJpaRepository;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.ModifyLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.command.RegisterLectureCommand;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureInfoDTO;
import com.psh10066.lecturemanagement.lecture.application.port.in.dto.LectureModifyInfoDTO;
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
    private final LectureToCurriculumJpaRepository lectureToCurriculumJpaRepository;
    private final LecturerRepository lecturerRepository;
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
        List<Curriculum> curriculumList = curriculumJpaRepository.findAllById(curriculumIds).stream()
            .map(CurriculumJpaEntity::toModel)
            .toList();

        curriculumList.forEach(curriculum -> {
            String newLecturerName = lecturerMap.get(curriculum.curriculumId());

            Lecturer lecturer = lecturerRepository.findOrRegister(newLecturerName, user);
            if (!lecturer.lecturerId().equals(curriculum.lecturerId())) {
                Curriculum newCurriculum = curriculum.updateCurriculum(lecturer.lecturerId());
                curriculumJpaRepository.save(CurriculumJpaEntity.from(newCurriculum));
            }
        });
    }

    @Override
    public Curriculum register(User user, Long lectureId, RegisterLectureCommand.CurriculumDTO curriculumDTO) {
        Long lecturerId = StringUtils.isNotBlank(curriculumDTO.lecturerName())
            ? lecturerRepository.findOrRegister(curriculumDTO.lecturerName(), user).lecturerId()
            : null;

        final Curriculum curriculum = curriculumJpaRepository.save(CurriculumJpaEntity.from(
            Curriculum.createCurriculum(curriculumDTO.curriculumName(), lecturerId))
        ).toModel();

        lectureToCurriculumJpaRepository.save(new LectureToCurriculumJpaEntity(lectureId, curriculum.curriculumId()));

        curriculumDTO.sectionList().forEach(sectionDTO ->
            sectionRepository.register(curriculum.curriculumId(), sectionDTO)
        );

        return curriculum;
    }

    @Override
    public List<LectureInfoDTO.CurriculumDTO> findInfoByLectureId(Long lectureId) {
        List<CurriculumDetailDTO> curriculumList = curriculumJpaRepository.findAllByLectureId(lectureId);

        List<Long> curriculumIds = curriculumList.stream()
            .map(CurriculumDetailDTO::curriculumId)
            .toList();
        List<LectureInfoDTO.SectionDTO> sectionList = sectionRepository.findInfoByCurriculumIdIn(curriculumIds);

        return curriculumList.stream()
            .map(curriculum -> new LectureInfoDTO.CurriculumDTO(
                curriculum.curriculumId(),
                curriculum.curriculumName(),
                curriculum.lecturerName(),
                sectionList.stream()
                    .filter(sectionDTO -> sectionDTO.curriculumId().equals(curriculum.curriculumId()))
                    .toList()
            ))
            .toList();
    }

    @Override
    public List<LectureModifyInfoDTO.CurriculumDTO> findModifyInfoByLectureId(Long lectureId) {
        return curriculumJpaRepository.findAllByLectureId(lectureId).stream()
            .map(curriculumDetailDTO -> new LectureModifyInfoDTO.CurriculumDTO(
                curriculumDetailDTO.curriculumId(),
                curriculumDetailDTO.curriculumName(),
                curriculumDetailDTO.lecturerName()
            ))
            .toList();
    }
}
