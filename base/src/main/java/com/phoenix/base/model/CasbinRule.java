package com.phoenix.base.model;

import com.phoenix.common.util.TextUtil;
import lombok.*;

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

        if (!TextUtil.isNullOrEmpty(arg1)) {
            arguments.add(arg1);
        }

        if (!TextUtil.isNullOrEmpty(arg2)) {
            arguments.add(arg2);
        }

        if (!TextUtil.isNullOrEmpty(arg3)) {
            arguments.add(arg3);
        }

        if (!TextUtil.isNullOrEmpty(arg4)) {
            arguments.add(arg4);
        }

        if (!TextUtil.isNullOrEmpty(arg5)) {
            arguments.add(arg5);
        }

        if (!TextUtil.isNullOrEmpty(arg6)) {
            arguments.add(arg6);
        }

        return arguments;
    }

    public String[] getArgumentsAsArray() {
        List<String> arguments = getArguments();

        return arguments.toArray(new String[0]);
    }

}
