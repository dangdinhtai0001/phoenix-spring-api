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
@Table(name = "FW_RESOURCE_ACTION")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class ResourceActionEntity extends BaseEntity<Long> {

    @Column(name = "resource")
    private String resource;

    @Column(name = "action")
    private String action;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceActionEntity)) return false;
        ResourceActionEntity that = (ResourceActionEntity) o;
        return Objects.equals(getResource(), that.getResource()) && Objects.equals(getAction(), that.getAction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getResource(), getAction(), getDescription());
    }
}