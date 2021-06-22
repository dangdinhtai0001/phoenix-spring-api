/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/20/21, 4:01 PM
 */

package com.phoenix.api.entities.auth;

import com.phoenix.api.entities.base.BaseEntity;
import com.phoenix.api.entities.base.BaseEntityAudit;
import com.phoenix.api.entities.base.BaseEntityAudit;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FW_USER_STATUS")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString()
public class UserStatusEntity extends BaseEntity {
    @Column(name = "NAME", length = 20)
    private String name;

    @Column(name = "CODE")
    private int code;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;
}
