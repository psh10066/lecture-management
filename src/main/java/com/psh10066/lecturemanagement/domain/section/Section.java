package com.psh10066.lecturemanagement.domain.section;

import com.psh10066.lecturemanagement.domain.common.AuditingFields;
import com.psh10066.lecturemanagement.domain.curriculum.Curriculum;
import com.psh10066.lecturemanagement.domain.study.Study;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.List;

@Comment("섹션")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Section extends AuditingFields {

    @Comment("섹션 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;

    @Comment("섹션 이름")
    @Column(nullable = false)
    private String sectionName;

    @Comment("커리큘럼 고유번호")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curriculum_id", nullable = false)
    private Curriculum curriculum;

    @OneToMany(mappedBy = "section")
    private List<Study> studyList;

    private Section(String sectionName, Curriculum curriculum) {
        this.sectionName = sectionName;
        this.curriculum = curriculum;
    }

    public static Section createSection(String sectionName, Curriculum curriculum) {
        return new Section(sectionName, curriculum);
    }
}
