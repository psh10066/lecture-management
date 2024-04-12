package com.psh10066.lecturemanagement.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterLecturerRequest(
    @NotBlank
    String lecturerName
) {
    public static RegisterLecturerRequest noArgs() {
        return new RegisterLecturerRequest(null);
    }
}
