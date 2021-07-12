/*
 * @Date created: 7/12/21, 10:49 PM
 * @Last-Modified: 7/12/21, 10:49 PM
 * @Username: Admin
 * @Author: Đặng Đình Tài
 */

package com.phoenix.sql.builder;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class UpdateBuilder extends AbstractSqlBuilder implements Serializable {
    private static final long serialVersionUID = 1;

    private String table;

    private List<String> sets = new LinkedList<>();

    private List<String> wheres = new LinkedList<>();

    public UpdateBuilder(String table) {
        this.table = table;
    }

    public UpdateBuilder set(String expr) {
        sets.add(expr);
        return this;
    }

    public UpdateBuilder where(String expr) {
        wheres.add(expr);
        return this;
    }

    @Override
    public String build() {
        StringBuilder sql = new StringBuilder("update ").append(table);
        appendList(sql, sets, " set ", ", ");
        appendList(sql, wheres, " where ", " and ");
        return sql.toString();
    }
}
