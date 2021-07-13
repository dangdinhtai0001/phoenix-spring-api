/*
 * @Date created: 7/12/21, 8:43 PM
 * @Last-Modified: 7/12/21, 8:43 PM
 * @Username: Admin
 * @Author: Đặng Đình Tài
 */

package com.phoenix.sql.builder;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Builder for building SQL insert statements.
 */
public class InsertBuilder extends AbstractSqlBuilder implements Serializable {
    private static final long serialVersionUID = 1;

    private final String table;

    private final List<String> columns = new LinkedList<>();

    private final List<String> values = new LinkedList<>();

    /**
     * Constructor.
     *
     * @param table Name of the table into which we'll be inserting.
     */
    public InsertBuilder(String table) {
        this.table = table;
    }

    /**
     * Inserts a column name, value pair into the SQL.
     *
     * @param column Name of the table column.
     * @param value  Value to substitute in. InsertBuilder does *no* interpretation
     *               of this. If you want a string constant inserted, you must
     *               provide the single quotes and escape the internal quotes. It
     *               is more common to use a question mark or a token in the style
     *               of {@link ParameterizedPreparedStatementCreator}, e.g. ":foo".
     */
    public InsertBuilder set(String column, String value) {
        columns.add(column);
        values.add(value);
        return this;
    }

    @Override
    public String build() {
        StringBuilder sql = new StringBuilder("insert into ").append(table).append(" (");
        appendList(sql, columns, "", ", ");
        sql.append(") values (");
        appendList(sql, values, "", ", ");
        sql.append(")");
        return sql.toString();
    }
}
