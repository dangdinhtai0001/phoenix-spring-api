package com.phoenix.api.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private T id;


    @Override
    public boolean equals(Object that) {
        return this == that ||
                that instanceof BaseEntity && Objects.equals(id, ((BaseEntity) that).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
