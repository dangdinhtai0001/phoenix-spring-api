package com.phoenix.api.entities.auth;

import com.phoenix.api.base.entities.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Table(name = "FW_PERMISSION")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PermissionEntity extends BaseEntity {
    @Column(name = "code")
    private int code;

    @Column(name = "name")
    private String name;

    @Column(name = "char_")
    private String char_;

    @Column(name = "description")
    private String description;
}
