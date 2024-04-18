package com.psh10066.lecturemanagement.infrastructure.paging;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class PagingMaker {

    @Value("${spring.data.web.pageable.page-parameter}")
    private String pageParam;

    private final HttpServletRequest httpServletRequest;

    public PagingDTO getPaging(Pageable pageable, Page<?> page) {
        String pagingURI = "?";
        String queryString = httpServletRequest.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>(UriComponentsBuilder
                .fromUriString("?" + queryString)
                .build()
                .getQueryParams());
            queryParams.remove(pageParam);
            if (!queryParams.isEmpty()) {
                pagingURI = UriComponentsBuilder.newInstance()
                    .queryParams(queryParams)
                    .toUriString();
                pagingURI = URLDecoder.decode(pagingURI, StandardCharsets.UTF_8) + "&";
            }
        }
        pagingURI += pageParam + "=";
        return new PagingDTO(pageable, page, pagingURI);
    }
}
