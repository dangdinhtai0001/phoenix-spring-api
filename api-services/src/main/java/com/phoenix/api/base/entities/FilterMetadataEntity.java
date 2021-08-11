package com.phoenix.api.base.entities;


import com.phoenix.api.core.entity.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "FW_FILTER_METADATA")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class FilterMetadataEntity extends BaseEntity<Long> {

    @Column(name = "object_")
    private String object;

    @Column(name = "table_")
    private String table;

    @Column(name = "column_")
    private String column;

    @Column(name = "alias_")
    private String alias;

    @Column(name = "field")
    private String field;

    @Column(name = "field_type")
    private String fieldType;

    @Column(name = "is_filter")
    private int isFilter;

    @Column(name = "filter_type")
    private String filterType;

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof FilterMetadataEntity)) return false;
//        if (!super.equals(o)) return false;
        FilterMetadataEntity that = (FilterMetadataEntity) o;
        return getObject().equals(that.getObject()) && getField().equals(that.getField());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getObject(), getField());
    }
}
