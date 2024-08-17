package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.jpaclient.AuditingFields;
import com.psh10066.lecturemanagement.lecture.domain.Lecture;
import com.psh10066.lecturemanagement.lecture.domain.LecturePlatform;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("강의")
@Getter
@Entity(name = "lecture")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LectureJpaEntity extends AuditingFields {

    @Comment("강의 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Comment("강의 이름")
    @Column(nullable = false)
    private String lectureName;

    @Comment("강의 플랫폼")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LecturePlatform lecturePlatform;

    @Comment("강의 경로")
    private String lecturePath;

    @Comment("사용자 고유번호")
    @Column(nullable = false)
    private Long userId;

    public Lecture toModel() {
        return LectureMapper.INSTANCE.toModel(this);
    }

    public static LectureJpaEntity from(Lecture lecture) {
        return LectureMapper.INSTANCE.from(lecture);
    }
}
