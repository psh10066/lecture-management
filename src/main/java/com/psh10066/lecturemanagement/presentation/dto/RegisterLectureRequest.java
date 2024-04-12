package com.psh10066.lecturemanagement.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterLectureRequest(
    @NotBlank
    String lectureName,

    String lecturePath,

    @NotBlank
    String lectureInfo
) {
    public static RegisterLectureRequest noArgs() {
        return new RegisterLectureRequest(null, null, null);
    }
}
