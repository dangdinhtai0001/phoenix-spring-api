/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/20/21, 4:12 PM
 */

package com.phoenix.api.entities.auth;

import com.phoenix.api.entities.base.BaseEntity;
import com.phoenix.api.entities.base.BaseEntityAudit;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "FW_RESOURCE")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResourceEntity extends BaseEntityAudit<String> {
    @Column(name = "NAME", length = 20)
    private String name;

    @Column(name = "DETAILS", length = 50)
    private String details;

    @Column(name = "TYPE")
    private long type;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @OneToMany(mappedBy = "resources")
    private List<GroupResourcesMapping> GroupResourcesEntities;
}
