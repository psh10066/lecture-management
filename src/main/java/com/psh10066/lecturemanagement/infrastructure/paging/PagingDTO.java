package com.psh10066.lecturemanagement.infrastructure.paging;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Getter
public class PagingDTO {

    private final int pageNumber;
    private final long totalElements;
    private final long rowStartNumber;
    private final int pagingStartNumber;
    private final int pagingEndNumber;
    private final String pagingURI;

    /**
     * [검색 시 pageNumber, pageSize에 0 이하의 값이 들어오는 경우 Pageable에서 자동으로 기본값 적용됨]
     * pageNumber : 0
     * pageSize : ${spring.data.web.pageable.default-page-size}
     */
    public PagingDTO(Pageable pageable, Page<?> page, String pagingURI) {
        int pagingMaxDistance = 2; // 페이징 앞뒤 개수

        // Pageable default zero based index 대응
        this.pageNumber = pageable.getPageNumber() + 1;
        this.totalElements = page.getTotalElements();

        if (this.pageNumber > page.getTotalPages()) {
            // 비정상적인 페이지 번호 혹은 page.getTotalPages()가 0일 때
            this.rowStartNumber = 0;
            this.pagingStartNumber = this.pageNumber;
            this.pagingEndNumber = this.pageNumber;
        } else {
            this.rowStartNumber = page.getTotalElements() - (long) pageable.getPageSize() * pageable.getPageNumber();
            this.pagingStartNumber = Math.max(1, this.pageNumber - pagingMaxDistance - (Math.max(0, this.pageNumber + pagingMaxDistance - page.getTotalPages())));
            this.pagingEndNumber = Math.min(page.getTotalPages(), this.pageNumber + pagingMaxDistance + (Math.max(0, pagingMaxDistance + 1 - this.pageNumber)));
        }
        this.pagingURI = pagingURI;
    }
}
