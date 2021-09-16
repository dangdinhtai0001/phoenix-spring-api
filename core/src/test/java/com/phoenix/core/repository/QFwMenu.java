package com.phoenix.core.repository;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QFwMenu is a Querydsl query type for QFwMenu
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QFwMenu extends com.querydsl.sql.RelationalPathBase<QFwMenu> {

    private static final long serialVersionUID = -1420725683;

    public static final QFwMenu fwMenu = new QFwMenu("fw_menu");

    public final StringPath description = createString("description");

    public final StringPath displayName = createString("displayName");

    public final NumberPath<Integer> displayOrder = createNumber("displayOrder", Integer.class);

    public final StringPath icon = createString("icon");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isHidden = createBoolean("isHidden");

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final NumberPath<Integer> parentId = createNumber("parentId", Integer.class);

    public final StringPath path = createString("path");

    public final StringPath userGroupsRequired = createString("userGroupsRequired");

    public final com.querydsl.sql.PrimaryKey<QFwMenu> primary = createPrimaryKey(id);

    public QFwMenu(String variable) {
        super(QFwMenu.class, forVariable(variable), "null", "fw_menu");
        addMetadata();
    }

    public QFwMenu(String variable, String schema, String table) {
        super(QFwMenu.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QFwMenu(String variable, String schema) {
        super(QFwMenu.class, forVariable(variable), schema, "fw_menu");
        addMetadata();
    }

    public QFwMenu(Path<? extends QFwMenu> path) {
        super(path.getType(), path.getMetadata(), "null", "fw_menu");
        addMetadata();
    }

    public QFwMenu(PathMetadata metadata) {
        super(QFwMenu.class, metadata, "null", "fw_menu");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(description, ColumnMetadata.named("description").withIndex(6).ofType(Types.VARCHAR).withSize(255));
        addMetadata(displayName, ColumnMetadata.named("display_name").withIndex(2).ofType(Types.VARCHAR).withSize(45).notNull());
        addMetadata(displayOrder, ColumnMetadata.named("display_order").withIndex(5).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(icon, ColumnMetadata.named("icon").withIndex(9).ofType(Types.VARCHAR).withSize(255));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(isHidden, ColumnMetadata.named("is_hidden").withIndex(8).ofType(Types.BIT).withSize(1));
        addMetadata(level, ColumnMetadata.named("level").withIndex(10).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(parentId, ColumnMetadata.named("parent_id").withIndex(4).ofType(Types.INTEGER).withSize(10));
        addMetadata(path, ColumnMetadata.named("path").withIndex(3).ofType(Types.VARCHAR).withSize(45).notNull());
        addMetadata(userGroupsRequired, ColumnMetadata.named("user_groups_required").withIndex(7).ofType(Types.VARCHAR).withSize(45));
    }

}

