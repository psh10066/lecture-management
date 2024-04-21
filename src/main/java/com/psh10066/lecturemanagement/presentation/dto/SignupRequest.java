package com.psh10066.lecturemanagement.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
    @NotBlank
    String username,

    @NotBlank
    String password
) {
    public static SignupRequest noArgs() {
        return new SignupRequest(null, null);
    }
}
