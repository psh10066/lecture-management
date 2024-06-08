package com.psh10066.lecturemanagement.user.adapter.in.web.request;

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
