/*
 * @Date created: 7/12/21, 9:14 PM
 * @Last-Modified: 7/12/21, 9:14 PM
 * @Username: Admin
 * @Author: Đặng Đình Tài
 */

package com.phoenix.sql.builder;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Builder for creating SQL delete statements.
 */
public class DeleteBuilder extends AbstractSqlBuilder implements Serializable {
    private static final long serialVersionUID = 1;

    private final String table;

    private final List<String> wheres = new LinkedList<>();

    public DeleteBuilder(String table) {
        this.table = table;
    }

    public DeleteBuilder where(String expr) {
        wheres.add(expr);
        return this;
    }

    @Override
    public String build() {
        StringBuilder sql = new StringBuilder("delete from ").append(table);
        appendList(sql, wheres, " where ", " and ");
        return sql.toString();
    }
}
