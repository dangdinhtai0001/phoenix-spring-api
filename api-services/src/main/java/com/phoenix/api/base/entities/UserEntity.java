package com.phoenix.api.base.entities;

import com.phoenix.api.core.entity.AuditEntity;

import javax.persistence.Column;
import java.util.Date;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "FW_USER")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserEntity extends AuditEntity<String, Long> {
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PASSWORD_SALT")
    private String passwordSalt;

    @Column(name = "HASH_ALGORITHM")
    private String hashAlgorithm;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "PASSWORD_REMINDER_TOKEN")
    private String passwordReminderToken;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Column(name = "PASSWORD_REMINDER_EXPIRE")
    private Date passwordReminderExpire;
}
