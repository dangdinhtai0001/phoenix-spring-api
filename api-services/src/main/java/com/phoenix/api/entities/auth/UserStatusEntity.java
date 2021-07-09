/*
 * @Author: Đặng Đình Tài
 * @Created_date: 7/9/21, 11:10 PM
 */

/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/20/21, 4:01 PM
 */

package com.phoenix.api.entities.auth;

import com.phoenix.api.base.entities.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

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
