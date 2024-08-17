package com.psh10066.lecturemanagement.lecture.adapter.out.persistence;

import com.psh10066.lecturemanagement.jpaclient.AuditingFields;
import com.psh10066.lecturemanagement.lecture.domain.Lecturer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("강사")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LecturerJpaEntity extends AuditingFields {

    @Comment("강사 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecturerId;

    @Comment("강사 이름")
    @Column(nullable = false)
    private String lecturerName;

    @Comment("사용자 고유번호")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    public static LecturerJpaEntity from(Lecturer lecturer) {
        return LecturerMapper.INSTANCE.from(lecturer);
    }

    public Lecturer toModel() {
        return LecturerMapper.INSTANCE.toModel(this);
    }
}
