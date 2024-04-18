package com.psh10066.lecturemanagement.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterFastcampusLectureRequest(
    @NotBlank
    String lectureName,

    String lecturePath,

    @NotBlank
    String lectureInfo
) {
    public static RegisterFastcampusLectureRequest noArgs() {
        return new RegisterFastcampusLectureRequest(null, null, null);
    }
}
