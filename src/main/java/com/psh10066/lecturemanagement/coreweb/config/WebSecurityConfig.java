package com.psh10066.lecturemanagement.coreweb.config;

import com.psh10066.lecturemanagement.coreweb.filter.ContentCachingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final Environment env;
    private final DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/lecture", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.GET.name()))
                .permitAll()
            )
            .rememberMe(rememberMe -> rememberMe
                .tokenRepository(persistentTokenRepository())
            )
            .authorizeHttpRequests(config -> config
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/signup")).permitAll()
                .anyRequest().hasRole("USER")
            )
            .addFilterAfter(new ContentCachingFilter(), HeaderWriterFilter.class)
        ;

        return http.build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(isH2Profile()); // persistent_logins 테이블 없는 경우 최초 생성 설정
        return jdbcTokenRepository;
    }

    private boolean isH2Profile() {
        return env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("test");
    }
}
