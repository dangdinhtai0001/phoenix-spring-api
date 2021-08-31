package com.phoenix.api.core.model;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QueryExpression {
    private String expression;
    private ExpressionType type;
    private List<Object> arguments;

    public QueryExpression(String expression, ExpressionType type, Object... arguments) {
        this.expression = expression;
        this.type = type;
        this.arguments = Arrays.asList(arguments);
    }

    public QueryExpression(String expression, String type, Object... arguments) {
        this.expression = expression;
        this.type = ExpressionType.valueOf(type);
        this.arguments = Arrays.asList(arguments);
    }


}
