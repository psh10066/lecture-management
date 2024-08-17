package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.lecture.adapter.in.web.request.ModifyLectureRequest;
import com.psh10066.lecturemanagement.lecture.application.port.out.CurriculumRepository;
import com.psh10066.lecturemanagement.lecture.application.port.out.LecturerRepository;
import com.psh10066.lecturemanagement.lecture.domain.Curriculum;
import com.psh10066.lecturemanagement.lecture.domain.Lecturer;
import com.psh10066.lecturemanagement.user.domain.User;
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

    @Override
    public Curriculum save(Curriculum curriculum) {
        return curriculumJpaRepository.save(curriculum);
    }

    @Override
    public void updateLecturer(List<ModifyLectureRequest.InnerDTO> command, User user) {
        Map<Long, String> lecturerMap = command.stream()
            .collect(Collectors.toMap(
                ModifyLectureRequest.InnerDTO::getCurriculumId,
                ModifyLectureRequest.InnerDTO::getLecturerName
            ));
        Set<Long> curriculumIds = lecturerMap.keySet();
        List<Curriculum> curriculumList = curriculumJpaRepository.findAllById(curriculumIds);

        curriculumList.forEach(curriculum -> {
            String newLecturerName = lecturerMap.get(curriculum.getCurriculumId());
            if (curriculum.getLecturerJpaEntity() == null || !curriculum.getCurriculumName().equals(newLecturerName)) {
                curriculum.updateCurriculum(lecturerRepository.save(Lecturer.createLecturer(newLecturerName, user.getUserId())));
            }
        });
    }
}
