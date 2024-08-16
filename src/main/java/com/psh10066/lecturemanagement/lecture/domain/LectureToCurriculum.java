package com.psh10066.lecturemanagement.lecture.domain;

import com.psh10066.lecturemanagement.jpaclient.AuditingFields;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.LectureJpaEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("강의, 커리큘럼 매핑 테이블")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureToCurriculum extends AuditingFields {

    @Comment("강의, 커리큘럼 매핑 테이블 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureToCurriculumId;

    @Comment("강의 고유번호")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false)
    private LectureJpaEntity lectureJpaEntity;

    @Comment("커리큘럼 고유번호")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id", nullable = false)
    private Curriculum curriculum;

    private LectureToCurriculum(LectureJpaEntity lectureJpaEntity, Curriculum curriculum) {
        this.lectureJpaEntity = lectureJpaEntity;
        this.curriculum = curriculum;
    }

    public static LectureToCurriculum createLectureToCurriculum(Lecture lecture, Curriculum curriculum) {
        return new LectureToCurriculum(LectureJpaEntity.from(lecture), curriculum);
    }
}
