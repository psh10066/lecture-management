package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.curriculum;

import com.psh10066.lecturemanagement.jpaclient.AuditingFields;
import com.psh10066.lecturemanagement.lecture.domain.Curriculum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("커리큘럼")
@Getter
@Entity(name = "curriculum")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CurriculumJpaEntity extends AuditingFields {

    @Comment("커리큘럼 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long curriculumId;

    @Comment("커리큘럼 이름")
    @Column(nullable = false)
    private String curriculumName;

    @Comment("강의 고유번호")
    @Column(name = "lecture_id")
    private Long lectureId;

    @Comment("강사 고유번호")
    @Column(name = "lecturer_id")
    private Long lecturerId;

    public Curriculum toModel() {
        return CurriculumMapper.INSTANCE.toModel(this);
    }

    public static CurriculumJpaEntity from(Curriculum curriculum) {
        return CurriculumMapper.INSTANCE.from(curriculum);
    }
}
