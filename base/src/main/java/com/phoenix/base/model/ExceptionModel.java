package com.phoenix.base.model;

import com.phoenix.core.model.DefaultException;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExceptionModel implements DefaultException {
    private Long id;
    private String code;
    private String message;
    private int httpCode;
}
