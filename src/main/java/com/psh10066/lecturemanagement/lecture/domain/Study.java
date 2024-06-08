package com.psh10066.lecturemanagement.lecture.domain;

import com.psh10066.lecturemanagement.domain.common.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalTime;

@Comment("학습")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Study extends AuditingFields {

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    private Study(String studyName, LocalTime studyTime, Section section) {
        this.studyName = studyName;
        this.studyTime = studyTime;
        this.section = section;
    }

    public static Study createStudy(String studyName, LocalTime studyTime, Section section) {
        return new Study(studyName, studyTime, section);
    }
}
