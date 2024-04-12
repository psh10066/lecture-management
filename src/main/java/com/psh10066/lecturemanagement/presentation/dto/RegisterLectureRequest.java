package com.psh10066.lecturemanagement.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterLectureRequest(
    @NotBlank
    String lectureName,

    @NotBlank
    String lectureInfo
) {
    public static RegisterLectureRequest noArgs() {
        return new RegisterLectureRequest(null, null);
    }
}
