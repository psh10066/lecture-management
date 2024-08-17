package com.psh10066.lecturemanagement.lecture.adapter.out.persistence.section;

import com.psh10066.lecturemanagement.jpaclient.AuditingFields;
import com.psh10066.lecturemanagement.lecture.domain.Section;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("섹션")
@Getter
@Entity(name = "section")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SectionJpaEntity extends AuditingFields {

    @Comment("섹션 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;

    @Comment("섹션 이름")
    @Column(nullable = false)
    private String sectionName;

    @Comment("커리큘럼 고유번호")
    @Column(name = "curriculum_id", nullable = false)
    private Long curriculumId;

    public static SectionJpaEntity from(Section section) {
        return SectionMapper.INSTANCE.from(section);
    }

    public Section toModel() {
        return SectionMapper.INSTANCE.toModel(this);
    }
}
