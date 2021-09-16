package com.phoenix.core.model;

import java.io.Serializable;

public interface DefaultException<T extends Serializable> {
    T getId();

    String getCode();

    String getMessage();

    int getHttpCode();
}
