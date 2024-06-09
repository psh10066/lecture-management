package com.psh10066.lecturemanagement.systemlog.adapter.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psh10066.lecturemanagement.core.UserDetailsWithId;
import com.psh10066.lecturemanagement.systemlog.application.port.in.CreateErrorLogCommand;
import com.psh10066.lecturemanagement.systemlog.application.port.in.CreateSuccessLogCommand;
import com.psh10066.lecturemanagement.systemlog.application.port.in.SystemLogService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper;
    private final SystemLogService systemLogService;

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

        if (ex == null) {
            systemLogService.createSuccessLog(new CreateSuccessLogCommand(userId, httpMethod, requestURL, requestParameters, requestHeaders, responseHeaders, this.getResponseBody(response)));
        } else {
            systemLogService.createErrorLog(new CreateErrorLogCommand(userId, httpMethod, requestURL, requestParameters, requestHeaders, responseHeaders, ex.getMessage()));
        }
    }

    private Long getUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsWithId user) {
            return user.getUserId();
        }
        return null;
    }

    private Map<String, Collection<String>> getHeaders(HttpServletRequest request) {
        Collection<String> headerNames = Collections.list(request.getHeaderNames());
        return headerNames.stream()
            .distinct()
            .collect(Collectors.toMap(
                headerName -> headerName,
                headerName -> Collections.list(request.getHeaders(headerName))
            ));
    }

    private Map<String, Collection<String>> getHeaders(HttpServletResponse response) {
        Collection<String> headerNames = response.getHeaderNames();
        return headerNames.stream()
            .distinct()
            .collect(Collectors.toMap(
                headerName -> headerName,
                response::getHeaders
            ));
    }

    @SneakyThrows
    private String getResponseBody(HttpServletResponse response) {
        ContentCachingResponseWrapper responseWrapper = (ContentCachingResponseWrapper) response;
        return new String(responseWrapper.getContentAsByteArray());
    }
}
