package com.phoenix.base.model;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResourceActionModel {
    private Long id;
    private String resource;
    private String action;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceActionModel)) return false;
        ResourceActionModel that = (ResourceActionModel) o;
        return Objects.equals(getResource(), that.getResource()) && Objects.equals(getAction(), that.getAction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getResource(), getAction(), getDescription());
    }
}
