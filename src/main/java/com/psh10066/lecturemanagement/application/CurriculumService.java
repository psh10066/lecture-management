package com.psh10066.lecturemanagement.application;

import com.psh10066.lecturemanagement.domain.curriculum.CurriculumRepository;
import com.psh10066.lecturemanagement.domain.lecturer.Lecturer;
import com.psh10066.lecturemanagement.domain.lecturer.LecturerRepository;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumInfoDTO;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumListDTO;
import com.psh10066.lecturemanagement.presentation.dto.CurriculumsRequest;
import com.psh10066.lecturemanagement.presentation.dto.ModifyCurriculumRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurriculumService {

    private final LecturerRepository lecturerRepository;
    private final CurriculumRepository curriculumRepository;

    public List<CurriculumListDTO> curriculumList(CurriculumsRequest request) {
        return curriculumRepository.findAllCurriculum(request.lectureId(), request.lecturerName(), request.curriculumName());
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
