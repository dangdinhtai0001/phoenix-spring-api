package com.phoenix.api.core.model;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SearchCriteriaRequest {
    private String key;
    private String searchOperation;
    private List<Object> arguments;

    public SearchCriteriaRequest(String key, String searchOperation, Object... arguments) {
        this.key = key;
        this.searchOperation = searchOperation;
        this.arguments = Arrays.asList(arguments);
    }

    public SearchCriteria getSearchCriteria() {
        return new SearchCriteria(key, SearchOperation.valueOf(searchOperation), arguments);
    }
}
