package com.phoenix.api.core.model;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BasePagination<T> {
    private int pageIndex;
    private long pageSize;
    private long totalItems;
    private List<T> items;

    public BasePagination(Page<T> page) {
        setPageIndex(page.getNumber());
        setPageSize(page.getSize());
        setTotalItems(page.getTotalElements());
        setItems(page.getContent());
    }
}
