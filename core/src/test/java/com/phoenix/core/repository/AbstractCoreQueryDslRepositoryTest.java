package com.phoenix.core.repository;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.sql.RelationalPathBase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AbstractCoreQueryDslRepositoryTest {
    @Test
    public void testGetTableName() {
        RelationalPathBase<QFwMenu> relationalPathBase = QFwMenu.fwMenu;
        System.out.println(relationalPathBase.getTableName());
    }

    @Test
    public void testGetSchema() {
        RelationalPathBase<QFwMenu> relationalPathBase = QFwMenu.fwMenu;
        System.out.println(relationalPathBase.getSchemaName());
    }

    @Test
    public void testGetPathBuilderMetadata() {
        RelationalPathBase<QFwMenu> relationalPathBase = QFwMenu.fwMenu;
        PathBuilder<QFwMenu> pathBuilder = new PathBuilder<>(QFwMenu.class, relationalPathBase.getTableName());
        System.out.println(pathBuilder.getMetadata());
    }

    @Test
    public void testGetPath() {
        RelationalPathBase<QFwMenu> relationalPathBase = QFwMenu.fwMenu;
        String[] columns = {"description", "displayName", "displayOrder", "icon"};

        List<Path<?>> pathList = relationalPathBase.getColumns();
        List<Path<?>> result = new ArrayList<>(columns.length);

        for (Path path : pathList) {
            for (String column : columns) {
                if (path.getMetadata().getName().equals(column)) {
                    result.add(path);
                    break;
                }
            }
        }


        for (Path path : result) {
            System.out.println(path);
        }
    }

    @Test
    public void testGetPath2() {
        PathBuilder<QFwMenu> pathBuilder = new PathBuilder(QFwMenu.class, "fw_menu");
        String[] columns = {"description", "displayName", "displayOrder", "icon"};


//        List<Path<?>> result = new ArrayList<>(columns.length);
//
//        for (Path path : pathList) {
//            for (String column : columns) {
//                if (path.getMetadata().getName().equals(column)) {
//                    result.add(path);
//                    break;
//                }
//            }
//        }
//
//
//        for (Path path : result) {
//            System.out.println(path);
//        }
    }

}