package com.psh10066.lecturemanagement.domain.lecture;

import com.psh10066.lecturemanagement.domain.common.AuditingFields;
import com.psh10066.lecturemanagement.domain.lecture.type.LecturePlatform;
import com.psh10066.lecturemanagement.domain.lecturetocurriculum.LectureToCurriculum;
import com.psh10066.lecturemanagement.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

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

    @Comment("강의 플랫폼")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LecturePlatform lecturePlatform;

    @Comment("강의 경로")
    private String lecturePath;

    @Comment("사용자 고유번호")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "lecture")
    private List<LectureToCurriculum> lectureToCurriculumList;

    private Lecture(String lectureName, LecturePlatform lecturePlatform, String lecturePath, User user) {
        this.lectureName = lectureName;
        this.lecturePlatform = lecturePlatform;
        this.lecturePath = lecturePath;
        this.user = user;
    }

    public static Lecture createLecture(String lectureName, LecturePlatform lecturePlatform, String lecturePath, User user) {
        return new Lecture(lectureName, lecturePlatform, lecturePath, user);
    }
}
