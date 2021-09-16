package com.phoenix.core.model.pagination;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaginationOption {
    private int page;
    private int itemsPerPage;
    private String[] sortAscending;
    private String[] sortDescending;
    private String[] fields;
}
