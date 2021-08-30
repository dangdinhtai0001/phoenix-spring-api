package com.phoenix.api.core.model;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderBy {
    private List<String> keys;
    private OrderDirection direction;

    public OrderBy(OrderDirection direction, String... keys) {
        this.direction = direction;
        this.keys = Arrays.asList(keys);
    }
}
