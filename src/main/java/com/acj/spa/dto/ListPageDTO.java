package com.acj.spa.dto;

import java.util.List;

public class ListPageDTO {
    List<?> content;
    long totalElements;

    public ListPageDTO(List<?> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
