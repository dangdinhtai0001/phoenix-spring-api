package com.phoenix.api.base.entities;

import com.phoenix.api.core.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FW_MENU")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class MenuEntity extends BaseEntity<Long> {
    @Column(name = "display_name")
    private String displayName;

    @Column(name = "path")
    private String path;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "display_order")
    private int displayOrder;

    @Column(name = "description")
    private String description;

    @Column(name = "user_groups_required")
    private String userGroupsRequired;

    @Column(name = "is_hidden")
    private String isHidden;
}
