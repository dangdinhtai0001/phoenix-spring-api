package com.phoenix.api.base.model;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CasbinRule {
    private String pType;
    private String arg1;
    private String arg2;
    private String arg3;

    public List<String> getArguments() {
        return Arrays.asList(arg1, arg2, arg3);
    }
}
