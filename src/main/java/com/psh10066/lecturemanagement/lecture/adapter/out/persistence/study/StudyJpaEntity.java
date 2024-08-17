package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.study;

import com.psh10066.lecturemanagement.jpaclient.AuditingFields;
import com.psh10066.lecturemanagement.lecture.domain.Study;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalTime;

@Comment("학습")
@Getter
@Entity(name = "study")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StudyJpaEntity extends AuditingFields {

    @Comment("학습 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyId;

    @Comment("학습 이름")
    @Column(nullable = false)
    private String studyName;

    @Comment("학습 시간")
    private LocalTime studyTime;

    @Comment("섹션 고유번호")
    @Column(name = "section_id", nullable = false)
    private Long sectionId;

    public static StudyJpaEntity from(Study study) {
        return StudyMapper.INSTANCE.from(study);
    }

    public Study toModel() {
        return StudyMapper.INSTANCE.toModel(this);
    }
}
