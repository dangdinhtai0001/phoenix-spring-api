package com.phoenix.api.entities.common;

import com.phoenix.api.entities.base.BaseEntity;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fw_menu")
@ToString(callSuper = true)
public class MenuEntity extends BaseEntity {
    private String name;
    private String href;
    private String title;
    private Integer parentId;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "href")
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuEntity that = (MenuEntity) o;
        return this.getId().equals(that.getId()) && Objects.equals(name, that.name) && Objects.equals(href, that.href) && Objects.equals(title, that.title) && Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), name, href, title, parentId);
    }


}
