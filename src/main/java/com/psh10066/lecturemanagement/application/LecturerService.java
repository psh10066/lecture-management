package com.psh10066.lecturemanagement.application;

import com.psh10066.lecturemanagement.domain.lecturer.Lecturer;
import com.psh10066.lecturemanagement.domain.lecturer.LecturerRepository;
import com.psh10066.lecturemanagement.presentation.dto.LecturerListDTO;
import com.psh10066.lecturemanagement.presentation.dto.RegisterLecturerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LecturerService {

    private final LecturerRepository lecturerRepository;

    public List<LecturerListDTO> lecturerList() {
        return lecturerRepository.findAllByOrderByLecturerNameAsc().stream()
            .map(LecturerListDTO::from)
            .toList();
    }

    public void registerLecturer(RegisterLecturerRequest request) {
        lecturerRepository.save(Lecturer.createLecturer(request.lecturerName()));
    }
}
