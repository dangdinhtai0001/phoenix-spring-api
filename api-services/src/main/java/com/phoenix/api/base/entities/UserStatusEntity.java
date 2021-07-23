package com.phoenix.api.base.entities;

import com.phoenix.api.core.entity.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Table(name = "FW_USER_STATUS")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString()
public class UserStatusEntity extends BaseEntity<Long> {
    @Column(name = "NAME", length = 20)
    private String name;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;
}
