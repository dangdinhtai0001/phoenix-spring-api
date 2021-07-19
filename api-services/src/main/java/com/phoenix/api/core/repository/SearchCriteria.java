package com.phoenix.api.core.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SearchCriteria {
    private String key;
    private SearchOperation searchOperation;
    private boolean isOrOperation;
    private List<Object> arguments;
}
