package com.psh10066.lecturemanagement.systemlog.adapter.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psh10066.lecturemanagement.systemlog.domain.SystemLog;
import com.psh10066.lecturemanagement.systemlog.application.port.out.SystemLogRepository;
import com.psh10066.lecturemanagement.user.domain.User;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper;
    private final SystemLogRepository systemLogRepository;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws JsonProcessingException {
        if (PathRequest.toStaticResources().atCommonLocations().matches(request)) {
            return;
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            return;
        }

        Long userId = this.getUserId();
        String httpMethod = request.getMethod();
        String requestURL = request.getRequestURL().toString();
        if (StringUtils.isNotBlank(request.getQueryString())) {
            requestURL += "?" + request.getQueryString();
        }

        String requestParameters = objectMapper.writeValueAsString(request.getParameterMap());
        String requestHeaders = objectMapper.writeValueAsString(this.getHeaders(request));
        String responseHeaders = objectMapper.writeValueAsString(this.getHeaders(response));

        SystemLog systemLog;
        if (ex == null) {
            systemLog = SystemLog.createSuccessLog(userId, httpMethod, requestURL, requestParameters, requestHeaders, responseHeaders, this.getResponseBody(response));
        } else {
            systemLog = SystemLog.createErrorLog(userId, httpMethod, requestURL, requestParameters, requestHeaders, responseHeaders, ex.getMessage());
        }
        systemLogRepository.save(systemLog);
    }

    private Long getUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User user) {
            return user.getUserId();
        }
        return null;
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeaders(headerName).toString());
        }
        return headers;
    }

    private Map<String, String> getHeaders(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>();
        Collection<String> headerNames = response.getHeaderNames();
        for (String headerName : headerNames) {
            headers.put(headerName, response.getHeaders(headerName).toString());
        }
        return headers;
    }

    @SneakyThrows
    private String getResponseBody(HttpServletResponse response) {
        ContentCachingResponseWrapper responseWrapper = (ContentCachingResponseWrapper) response;
        return new String(responseWrapper.getContentAsByteArray());
    }
}
