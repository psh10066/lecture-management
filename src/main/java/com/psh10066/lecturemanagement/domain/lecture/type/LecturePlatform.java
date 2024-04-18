package com.psh10066.lecturemanagement.domain.lecture.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LecturePlatform {

    FASTCAMPUS("패스트캠퍼스", "fastcampus"),
    INFLEARN("인프런", "inflearn");

    private String name;
    private String path;
}
