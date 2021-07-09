/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 11:06 AM
 */

/*
 * @Author: Đặng Đình Tài
 * @Created_date: 6/26/21, 11:06 AM
 */

package com.phoenix.api.entities.common;

import com.phoenix.api.base.entities.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Component
@Entity
@Table(name = "FW_EXCEPTION")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper=true)
public class ExceptionEntity extends BaseEntity {
    @Column(name = "CODE_")
    private String code;

    @Column(name = "MESSAGE_")
    private String message;

    @Column(name = "HTTP_CODE")
    private int httpCode;
}
