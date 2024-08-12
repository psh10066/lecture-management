package com.psh10066.lecturemanagement.user.domain;

import com.psh10066.lecturemanagement.core.UserDetailsWithId;
import com.psh10066.lecturemanagement.core.util.DateTimeFields;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends DateTimeFields implements UserDetailsWithId {

    private Long userId;
    private String username;
    private String password;
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

    public static User createUser(String username, String password) {
        return User.builder()
            .username(username)
            .password(password)
            .enabled(true)
            .build();
    }
}
