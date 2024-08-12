package com.psh10066.lecturemanagement.user.adapter.out.persistence;

import com.psh10066.lecturemanagement.jpaclient.AuditingFields;
import com.psh10066.lecturemanagement.jpaclient.converter.PasswordConverter;
import com.psh10066.lecturemanagement.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@Comment("사용자")
@Getter
@Entity(name = "user")
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserJpaEntity extends AuditingFields {

    @Comment("사용자 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Comment("사용자 아이디")
    @Column(nullable = false)
    private String username;

    @Comment("사용자 비밀번호")
    @Column(nullable = false)
    @Convert(converter = PasswordConverter.class)
    private String password;

    @Comment("사용자 활성화 여부")
    @Column(nullable = false)
    private boolean enabled;

    public static UserJpaEntity from(User user) {
        return UserMapper.INSTANCE.from(user);
    }

    public User toModel() {
        return UserMapper.INSTANCE.toModel(this);
    }
}
