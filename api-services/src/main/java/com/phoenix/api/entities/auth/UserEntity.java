/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/20/21, 3:33 PM
 */

package com.phoenix.api.entities.auth;

import com.phoenix.api.entities.base.BaseEntityAudit;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Chúng ta sẽ đặt annotation @ManyToMany cùng với khai báo một biến Collection trong cả hai entity UserEntity
 * và Group và để thể hiện mối quan hệ nhiều nhiều, chúng ta sẽ sử dụng một annotation khác tên là
 *
 * @JoinTable trong một trong hai entity này. Các bạn có thể đặt annotation @JoinTable ở đâu cũng được,
 * nếu annotation này nằm trong entity @UserEntity thì trong entity Group các bạn phải khai báo thêm thuộc
 * tính mappedBy trong annotation @ManyToMany và ngược lại.
 */
@Entity
@Table(name = "FW_USER")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserEntity extends BaseEntityAudit<String> {
    @Column(name = "USERNAME", length = 50)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PASSWORD_SALT")
    private String passwordSalt;

    @Column(name = "HASH_ALGORITHM", length = 50)
    private String hashAlgorithm;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "PASSWORD_REMINDER_TOKEN")
    private String passwordReminderToken;

    @Column(name = "REFRESH_TOKEN", length = 200)
    private String refreshToken;

    @Column(name = "PASSWORD_REMINDER_EXPIRE")
    private Date passwordReminderExpire;
}
