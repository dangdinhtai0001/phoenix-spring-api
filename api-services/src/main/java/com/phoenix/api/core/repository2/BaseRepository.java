package com.phoenix.api.core.repository2;

import com.phoenix.api.core.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity<ID>, ID> extends JpaRepository<T, ID>, DefaultRepository<T, ID> {
}
