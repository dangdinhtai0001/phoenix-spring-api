/*
 *  @Author Đặng Đình Tài
 *  @Created date: 6/20/21, 3:57 PM
 */

package com.phoenix.api.entities.auth;

import com.phoenix.api.entities.base.BaseEntityAudit;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "FW_GROUP")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GroupEntity extends BaseEntityAudit<String> {

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(mappedBy = "group")
    private List<UserEntity> users;

}
