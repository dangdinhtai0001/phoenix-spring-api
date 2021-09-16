package com.phoenix.core.model.query;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderByRequest {
    private List<String> keys;
    private String direction;

    public OrderBy getOderBy() {
        return new OrderBy(keys, OrderDirection.valueOf(direction));
    }
}
