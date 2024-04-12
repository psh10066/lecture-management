package com.psh10066.lecturemanagement.domain.lecture;

import com.psh10066.lecturemanagement.domain.common.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("강의")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture extends AuditingFields {

    @Comment("강의 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Comment("강의 이름")
    @Column(nullable = false)
    private String lectureName;

    private Lecture(String lectureName) {
        this.lectureName = lectureName;
    }

    public static Lecture createLecture(String lectureName) {
        return new Lecture(lectureName);
    }
}
