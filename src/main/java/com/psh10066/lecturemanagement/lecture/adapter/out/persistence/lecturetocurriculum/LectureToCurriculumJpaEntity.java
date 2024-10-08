package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.lecturetocurriculum;

import com.psh10066.lecturemanagement.jpaclient.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("강의, 커리큘럼 매핑 테이블")
@Getter
@Entity(name = "lecture_to_curriculum")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureToCurriculumJpaEntity extends AuditingFields {

    @Comment("강의, 커리큘럼 매핑 테이블 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureToCurriculumId;

    @Comment("강의 고유번호")
    @Column(name = "lecture_id", nullable = false)
    private Long lectureId;

    @Comment("커리큘럼 고유번호")
    @Column(name = "curriculum_id", nullable = false)
    private Long curriculumId;

    public LectureToCurriculumJpaEntity(Long lectureId, Long curriculumId) {
        this.lectureId = lectureId;
        this.curriculumId = curriculumId;
    }
}
