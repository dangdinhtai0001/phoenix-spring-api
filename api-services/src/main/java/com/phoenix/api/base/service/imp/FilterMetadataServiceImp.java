package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.entities.FilterMetadata;
import com.phoenix.api.base.repositories.FilterMetadataRepository;
import com.phoenix.api.base.service.FilterMetadataService;
import com.phoenix.api.core.repository.specification.Specifications;
import com.phoenix.api.core.service.AbstractBaseService;
import com.phoenix.common.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service(BeanIds.FILTER_METADATA_SERVICES)
public class FilterMetadataServiceImp extends AbstractBaseService implements FilterMetadataService {
    private final FilterMetadataRepository filterMetadataRepository;

    protected FilterMetadataServiceImp(
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities,
            @Qualifier(BeanIds.FILTER_METADATA_REPOSITORY_IMP) FilterMetadataRepository filterMetadataRepository
    ) {
        super(exceptionEntities);
        this.filterMetadataRepository = filterMetadataRepository;
    }

    public List<FilterMetadata> saveDataByClassName(String className) {
        try {
            List<FilterMetadata> filterMetadataList = new LinkedList<>();
            List<String> fieldNamesList = new LinkedList<>();

            Class aClass = Class.forName(className);
            List<Field> fields = ReflectionUtil.getAllFields(aClass);

            FilterMetadata filterMetadata;
            for (Field field : fields) {
                filterMetadata = new FilterMetadata();
                filterMetadata.setObject(className);
                filterMetadata.setField(field.getName());
                filterMetadata.setFieldType(field.getType().getName());

                filterMetadataList.add(filterMetadata);

                fieldNamesList.add(field.getName());
            }

            Specification<FilterMetadata> specification = Specifications.<FilterMetadata>and()
                    .eq("object", className)
                    .in("field", fieldNamesList)
                    .build();

            List<FilterMetadata> exitsFilterMetadata = filterMetadataRepository.findAll(specification);

            filterMetadataList.removeAll(exitsFilterMetadata);

            filterMetadataRepository.saveAllAndFlush(filterMetadataList);

            return filterMetadataList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
