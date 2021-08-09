package com.phoenix.api.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * configure JPA to automatically persist the CreatedBy, CreatedDate, LastModifiedBy, and LastModifiedDate columns for any entity.
 *
 * @CreatedDate - Declares a field as the one representing the date the entity containing the field was created.
 * @LastModifiedDate - Declares a field as the one representing the date the entity containing the field was recently modified.
 * @CreatedBy- Declares a field as the one representing the principal that created the entity containing the field.
 * @LastModifiedBy - Declares a field as the one representing the principal that recently modified the entity containing the field.
 * The Spring Data JPA approach abstracts working with JPA callbacks and provides us these fancy annotations to automatically save and update auditing entities.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AuditEntity<U, ID extends Serializable> extends BaseEntity<ID> {
    @CreatedBy
    @Column(name = "CREATED_BY", columnDefinition = "varchar(50) default 'NONE'")
    protected U createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED", columnDefinition = "datetime default current_timestamp")
    protected Date createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY", columnDefinition = "varchar(50) default 'NONE'")
    protected U lastModifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_MODIFIED_DATE", columnDefinition = "datetime default current_timestamp on update current_timestamp")
    protected Date lastModifiedDate;
}
