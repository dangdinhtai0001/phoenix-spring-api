package com.phoenix.api.base.entities;

import com.phoenix.api.core.entity.BaseEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity
@Table(name = "FW_EXCEPTION")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper=true)
public class ExceptionEntity extends BaseEntity<Long> {
    @Column(name = "CODE_")
    private String code;

    @Column(name = "MESSAGE_")
    private String message;

    @Column(name = "HTTP_CODE")
    private int httpCode;
}
