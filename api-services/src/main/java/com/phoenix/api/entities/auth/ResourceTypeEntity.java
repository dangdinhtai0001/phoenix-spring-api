/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/20/21, 4:11 PM
 */

package com.phoenix.api.entities.auth;

import com.phoenix.api.entities.base.BaseEntity;
import com.phoenix.api.entities.base.BaseEntityAudit;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Table(name = "FW_RESOURCE_TYPE")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResourceTypeEntity  extends BaseEntity {
    @Column(name = "NAME", length = 20)
    private String name;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;
}
