package com.psh10066.lecturemanagement.infrastructure.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psh10066.lecturemanagement.domain.systemlog.SystemLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;
    private final SystemLogRepository systemLogRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor(objectMapper, systemLogRepository));
    }
}
