package com.psh10066.lecturemanagement.domain.lecturer;

import com.psh10066.lecturemanagement.domain.common.AuditingFields;
import com.psh10066.lecturemanagement.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Comment("강사")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecturer extends AuditingFields {

    @Comment("강사 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lecturerId;

    @Comment("강사 이름")
    @Column(nullable = false)
    private String lecturerName;

    @Comment("사용자 고유번호")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Lecturer(String lecturerName, User user) {
        this.lecturerName = lecturerName;
        this.user = user;
    }

    public static Lecturer createLecturer(String lecturerName, User user) {
        return new Lecturer(lecturerName, user);
    }
}
