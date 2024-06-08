package com.psh10066.lecturemanagement.lecture.adapter.in.web.request;

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
