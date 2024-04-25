package com.psh10066.lecturemanagement.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ModifyLectureRequest {
    private List<InnerDTO> curriculumList;

    @Setter
    @Getter
    public static class InnerDTO {
        private Long curriculumId;
        private String lecturerName;
    }
}
