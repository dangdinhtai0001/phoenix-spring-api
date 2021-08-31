package com.phoenix.api.base.model;

import com.phoenix.api.base.entities.MenuEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class MenuWrapper extends MenuEntity {
    private List<MenuWrapper> children;

    public MenuWrapper(String displayName, String path, Integer parentId, int displayOrder, String description,
                       String userGroupsRequired, Boolean isHidden, String icon, List<MenuWrapper> children) {
        super(displayName, path, parentId, displayOrder, description, userGroupsRequired, isHidden, icon);
        this.children = children;
    }

    public MenuWrapper(MenuEntity menuEntity, List<MenuWrapper> children) {
        super(menuEntity.getDisplayName(), menuEntity.getPath(), menuEntity.getParentId(),
                menuEntity.getDisplayOrder(), menuEntity.getDescription(), menuEntity.getUserGroupsRequired(),
                menuEntity.getIsHidden(), menuEntity.getIcon());
        this.children = children;
    }

    public MenuWrapper(MenuEntity menuEntity) {
        super(menuEntity.getDisplayName(), menuEntity.getPath(), menuEntity.getParentId(),
                menuEntity.getDisplayOrder(), menuEntity.getDescription(), menuEntity.getUserGroupsRequired(),
                menuEntity.getIsHidden(), menuEntity.getIcon());

    }
}
