package com.psh10066.lecturemanagement.domain.curriculum;

import com.psh10066.lecturemanagement.domain.common.AuditingFields;
import com.psh10066.lecturemanagement.domain.lecturer.Lecturer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("커리큘럼")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Curriculum extends AuditingFields {

    @Comment("커리큘럼 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long curriculumId;

    @Comment("커리큘럼 이름")
    @Column(nullable = false)
    private String curriculumName;

    @Comment("강사 고유번호")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    private Curriculum(String curriculumName, Lecturer lecturer) {
        this.curriculumName = curriculumName;
        this.lecturer = lecturer;
    }

    public static Curriculum createCurriculum(String curriculumName, Lecturer lecturer) {
        return new Curriculum(curriculumName, lecturer);
    }
}
