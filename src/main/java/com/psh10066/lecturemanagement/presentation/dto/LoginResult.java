package com.psh10066.lecturemanagement.presentation.dto;

public record LoginResult(
    String error,
    String logout
) {
    public boolean isError() {
        return "".equals(error);
    }

    public boolean isLogout() {
        return "".equals(logout);
    }
}
