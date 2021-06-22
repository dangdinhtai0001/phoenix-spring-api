package com.phoenix.api.repositories.auth;

import com.phoenix.api.constant.BeanIds;
import com.phoenix.api.entities.auth.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = BeanIds.USER_STATUS_REPOSITORY)
public interface UserStatusRepository extends JpaRepository<UserStatusEntity, Long> {

    @Query(value = "select s.id, s.code, s.name, s.description from fw_user_status s order by s.code",
            nativeQuery = true)
    public List<UserStatusEntity> findAll();
}
