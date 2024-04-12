package com.psh10066.lecturemanagement.domain.lecturer;

import com.psh10066.lecturemanagement.domain.common.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("강사")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecturer extends AuditingFields {

    @Comment("강사 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecturerId;

    @Comment("강사 이름")
    @Column(nullable = false)
    private String lecturerName;

    private Lecturer(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public static Lecturer createLecturer(String lecturerName) {
        return new Lecturer(lecturerName);
    }
}
