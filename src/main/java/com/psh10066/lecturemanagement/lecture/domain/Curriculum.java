package com.psh10066.lecturemanagement.lecture.domain;

import com.psh10066.lecturemanagement.jpaclient.AuditingFields;
import com.psh10066.lecturemanagement.lecture.adapter.out.persistence.LecturerJpaEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

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
    private LecturerJpaEntity lecturerJpaEntity;

    @OneToMany(mappedBy = "curriculum")
    private List<Section> sectionList;

    private Curriculum(String curriculumName, LecturerJpaEntity lecturerJpaEntity) {
        this.curriculumName = curriculumName;
        this.lecturerJpaEntity = lecturerJpaEntity;
    }

    public static Curriculum createCurriculum(String curriculumName, Lecturer lecturer) {
        return new Curriculum(curriculumName, LecturerJpaEntity.from(lecturer));
    }

    public void updateCurriculum(Lecturer lecturer) {
        this.lecturerJpaEntity = LecturerJpaEntity.from(lecturer);
    }
}
