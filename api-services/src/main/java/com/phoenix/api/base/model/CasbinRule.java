package com.phoenix.api.base.model;

import lombok.*;

import java.util.Arrays;
import java.util.LinkedList;
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
    private String arg4;
    private String arg5;
    private String arg6;

    public List<String> getArguments() {
        List<String> arguments = new LinkedList<>();

        if (arg1 != null && !arg1.isEmpty()) {
            arguments.add(arg1);
        }

        if (arg2 != null && !arg2.isEmpty()) {
            arguments.add(arg2);
        }

        if (arg3 != null && !arg3.isEmpty()) {
            arguments.add(arg3);
        }

        if (arg4 != null && !arg4.isEmpty()) {
            arguments.add(arg4);
        }

        if (arg5 != null && !arg5.isEmpty()) {
            arguments.add(arg5);
        }

        if (arg6 != null && !arg6.isEmpty()) {
            arguments.add(arg6);
        }

        return arguments;
    }

    public String[] getArgumentsAsArray() {
        List<String> arguments = getArguments();

        return arguments.toArray(new String[0]);
    }

}
