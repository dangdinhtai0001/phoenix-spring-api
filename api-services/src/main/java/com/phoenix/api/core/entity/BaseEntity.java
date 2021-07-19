package com.phoenix.api.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class BaseEntity<T> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private T id;
}
