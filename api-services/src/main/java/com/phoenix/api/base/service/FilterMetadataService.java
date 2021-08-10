package com.phoenix.api.base.service;

import java.util.List;

public interface FilterMetadataService {
    void saveDataByClassName(String className);

    List saveDataByListClassName(List<String> listClassName);
}
