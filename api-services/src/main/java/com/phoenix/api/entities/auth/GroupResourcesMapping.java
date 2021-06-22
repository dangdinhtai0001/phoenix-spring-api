/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/20/21, 4:30 PM
 */

package com.phoenix.api.entities.auth;

import com.phoenix.api.entities.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "FW_GROUP_RESOURCES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GroupResourcesMapping extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private ResourceEntity resources;


    @Column(name = "permission")
    private String permissions;
}
