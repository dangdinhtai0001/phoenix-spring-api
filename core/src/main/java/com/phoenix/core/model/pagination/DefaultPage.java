package com.phoenix.core.model.pagination;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DefaultPage<T> {
    private int pageIndex;
    private long pageSize;
    private long totalItems;
    private List<T> items;

    public DefaultPage(Page<T> page) {
        setPageIndex(page.getNumber());
        setPageSize(page.getSize());
        setTotalItems(page.getTotalElements());
        setItems(page.getContent());
    }
}
