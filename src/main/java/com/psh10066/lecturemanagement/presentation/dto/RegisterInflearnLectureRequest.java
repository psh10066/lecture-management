package com.psh10066.lecturemanagement.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterInflearnLectureRequest(
    @NotBlank
    String lecturePath
) {
    public static RegisterInflearnLectureRequest noArgs() {
        return new RegisterInflearnLectureRequest(null);
    }
}
