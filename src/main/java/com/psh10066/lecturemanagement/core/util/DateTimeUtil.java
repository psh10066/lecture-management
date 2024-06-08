package com.psh10066.lecturemanagement.core.util;

import io.micrometer.common.util.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateTimeUtil {

    public static LocalTime parseTime(String s) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        String base = "00:0";
        return LocalTime.parse(base.substring(0, 8 - s.length()) + s);
    }
}
