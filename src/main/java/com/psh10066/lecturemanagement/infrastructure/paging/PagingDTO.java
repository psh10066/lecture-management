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

    public PagingDTO(Pageable pageable, Page<?> page, String pagingURI) {
        int pagingMaxDistance = 2; // 페이징 앞뒤 개수

        // Pageable default zero based index 대응
        this.pageNumber = pageable.getPageNumber() + 1;
        this.totalElements = page.getTotalElements();
        this.rowStartNumber = page.getTotalElements() - (long) pageable.getPageSize() * pageable.getPageNumber();
        this.pagingStartNumber = Math.max(1, this.pageNumber - pagingMaxDistance - (Math.max(0, this.pageNumber + pagingMaxDistance - page.getTotalPages())));
        this.pagingEndNumber = Math.min(page.getTotalPages(), this.pageNumber + pagingMaxDistance + (Math.max(0, pagingMaxDistance + 1 - this.pageNumber)));
        this.pagingURI = pagingURI;
    }
}
