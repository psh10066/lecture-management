package com.psh10066.lecturemanagement.domain.user;

import com.psh10066.lecturemanagement.domain.common.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Comment("사용자")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingFields implements UserDetails {

    @Comment("사용자 고유번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Comment("사용자 아이디")
    @Column(nullable = false)
    private String username;

    @Comment("사용자 비밀번호")
    @Column(nullable = false)
    private String password;

    @Comment("사용자 활성화 여부")
    @Column(nullable = false)
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }

    private User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }

    public static User createUser(String username, String password) {
        return new User(username, password);
    }
}
