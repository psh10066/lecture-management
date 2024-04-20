package com.psh10066.lecturemanagement.application;

import com.psh10066.lecturemanagement.domain.curriculum.CurriculumRepository;
import com.psh10066.lecturemanagement.domain.lecturer.Lecturer;
import com.psh10066.lecturemanagement.domain.lecturer.LecturerRepository;
import com.psh10066.lecturemanagement.domain.user.User;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumInfoDTO;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumListDTO;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumsRequest;
import com.psh10066.lecturemanagement.presentation.dto.ModifyCurriculumRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CurriculumService {

    private final LecturerRepository lecturerRepository;
    private final CurriculumRepository curriculumRepository;

    public Page<CurriculumListDTO> curriculumList(User user, Pageable pageable, CurriculumsRequest request) {
        return curriculumRepository.findAllCurriculum(user, pageable, request.lectureId(), request.lecturerName(), request.curriculumName());
    }

    public CurriculumInfoDTO curriculumInfo(Long curriculumId) {
        return curriculumRepository.findCurriculum(curriculumId);
    }

    @Transactional
    public void modifyCurriculum(Long curriculumId, ModifyCurriculumRequest request) {
        curriculumRepository.findById(curriculumId).ifPresent(curriculum -> {
            Lecturer lecturer = request.lecturerId() != null ? lecturerRepository.findById(request.lecturerId()).orElse(null) : null;
            curriculum.updateCurriculum(lecturer);
        });
    }
}
