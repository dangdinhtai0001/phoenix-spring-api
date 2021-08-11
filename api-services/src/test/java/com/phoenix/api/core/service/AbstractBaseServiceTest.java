package com.phoenix.api.core.service;

import com.phoenix.api.base.entities.FilterMetadataEntity;
import com.phoenix.api.core.exception.SearchCriteriaException;
import com.phoenix.api.core.model.SearchCriteria;
import com.phoenix.api.core.model.SearchOperation;
import com.phoenix.common.util.TextUtil;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class AbstractBaseServiceTest extends AbstractBaseServiceWithFilterHelper {

    protected AbstractBaseServiceTest() {
        super(new LinkedList<>(), null);
    }

    @Test
    public void testGetPredicateBuilderFromSearchCriteria() throws SearchCriteriaException {
        List<SearchCriteria> searchCriteriaList = initSearchCriteriaList();
        List<FilterMetadataEntity> filterMetadataList = initFilterMetadataEntities();

        List<SearchCriteria> result = mapBusinessObject2Table(searchCriteriaList, filterMetadataList);

        for (SearchCriteria searchCriteria : result) {
            System.out.println(searchCriteria);
        }
    }

    public List<SearchCriteria> mapBusinessObject2Table(List<SearchCriteria> searchCriteriaList, List<FilterMetadataEntity> filterMetadataList) throws SearchCriteriaException {
        Map<String, FilterMetadataEntity> map = filterMetadataList.stream()
                .collect(Collectors.toMap(FilterMetadataEntity::getField, filterMetadataEntity -> filterMetadataEntity));

        FilterMetadataEntity filterMetadataEntity;
        for (SearchCriteria searchCriteria : searchCriteriaList) {
            filterMetadataEntity = map.getOrDefault(searchCriteria.getKey(), null);

            if (filterMetadataEntity == null
                    || TextUtil.isNullOrEmpty(filterMetadataEntity.getTable())
                    || TextUtil.isNullOrEmpty(filterMetadataEntity.getColumn()
            )) {
                throw new SearchCriteriaException(String.format("missing metadata about the field '%s'", searchCriteria.getKey()));
            }

            if (filterMetadataEntity.getAlias() == null || filterMetadataEntity.getAlias().isEmpty()) {
                searchCriteria.setKey(String.format("%s.%s", filterMetadataEntity.getTable(), filterMetadataEntity.getColumn()));
            } else {
                searchCriteria.setKey(String.format("%s.%s", filterMetadataEntity.getAlias(), filterMetadataEntity.getColumn()));
            }
        }
        return searchCriteriaList;
    }


    private List<SearchCriteria> initSearchCriteriaList() {
        List<SearchCriteria> searchCriteriaList = new LinkedList<>();

        searchCriteriaList.add(new SearchCriteria("username", SearchOperation.EQUAL, "123"));
        searchCriteriaList.add(new SearchCriteria("password", SearchOperation.EQUAL, "123"));
        searchCriteriaList.add(new SearchCriteria("name", SearchOperation.EQUAL, "123"));
        searchCriteriaList.add(new SearchCriteria("name_", SearchOperation.EQUAL, "123"));

        return searchCriteriaList;
    }

    private List<FilterMetadataEntity> initFilterMetadataEntities() {
        List<FilterMetadataEntity> filterMetadataList = new LinkedList<>();

        filterMetadataList.add(new FilterMetadataEntity("com.phoenix.api.business.model.User", "profile", "id", "", "id", "java.lang.Long", 1, ""));
        filterMetadataList.add(new FilterMetadataEntity("com.phoenix.api.business.model.User", "profile", "name", "", "name", "java.lang.String", 1, ""));
        filterMetadataList.add(new FilterMetadataEntity("com.phoenix.api.business.model.User", "profile", "date_of_birth", "", "dateOfBirth", "java.util.Date", 1, ""));
        filterMetadataList.add(new FilterMetadataEntity("com.phoenix.api.business.model.User", "profile", "gender", "", "gender", "java.lang.String", 1, ""));
        filterMetadataList.add(new FilterMetadataEntity("com.phoenix.api.business.model.User", "profile", "phone_number", "", "phoneNumber", "java.lang.String", 1, ""));
        filterMetadataList.add(new FilterMetadataEntity("com.phoenix.api.business.model.User", "profile", "avatar", "", "avatar", "java.lang.String", 1, ""));
        filterMetadataList.add(new FilterMetadataEntity("com.phoenix.api.business.model.User", "fw_user", "username", "fu", "username", "java.lang.String", 1, ""));
        filterMetadataList.add(new FilterMetadataEntity("com.phoenix.api.business.model.User", "fw_user", "password", "fu", "password", "java.lang.String", 1, ""));

        return filterMetadataList;
    }


}