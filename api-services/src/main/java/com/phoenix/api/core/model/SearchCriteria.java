package com.phoenix.api.core.model;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SearchCriteria {
    private String key;
    private SearchOperation searchOperation;
    private List<Object> arguments;

    public SearchCriteria(String key, SearchOperation searchOperation, Object... arguments) {
        this.key = key;
        this.searchOperation = searchOperation;
        this.arguments = Arrays.asList(arguments);
    }
}
