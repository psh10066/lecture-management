package com.psh10066.lecturemanagement.helper;

import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.ThrowableAssert;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CustomTestHelper {

    public static void assertBeanValidation(String fieldName, ThrowableAssert.ThrowingCallable callable) {
        assertThatThrownBy(callable)
            .isInstanceOfSatisfying(ConstraintViolationException.class, e ->
                assertThat(e.getConstraintViolations().stream()
                    .anyMatch(violation -> violation.getPropertyPath().toString().equals(fieldName))
                ).isTrue()
            );
    }
}
