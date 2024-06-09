package com.psh10066.lecturemanagement.core.util;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public abstract class DateTimeFields {

    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
