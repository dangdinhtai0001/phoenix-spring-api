/*
 * @Date created: 7/12/21, 8:53 PM
 * @Last-Modified: 7/12/21, 8:53 PM
 * @Username: Admin
 * @Author: Đặng Đình Tài
 */

package com.phoenix.common.builder;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SelectBuilder extends AbstractSqlBuilder implements Cloneable, Serializable {
    private static final long serialVersionUID = 1;

    private boolean distinct;

    private final List<Object> columns = new LinkedList<>();

    private final List<String> tables = new LinkedList<>();

    private final List<String> joins = new LinkedList<>();

    private final List<String> leftJoins = new LinkedList<>();

    private final List<String> wheres = new LinkedList<>();

    private final List<String> groupBys = new LinkedList<>();

    private final List<String> havings = new LinkedList<>();

    private final List<SelectBuilder> unions = new LinkedList<>();

    private List<String> unionClauses = new LinkedList<>();

    private final List<String> orderBys = new LinkedList<>();

    private int limit = 0;

    private int offset = 0;

    private boolean forUpdate;

    private boolean noWait;

    public SelectBuilder() {

    }

    public SelectBuilder(String table) {
        tables.add(table);
    }

    /**
     * Copy constructor. Used by {@link #clone()}.
     *
     * @param other SelectBuilder being cloned.
     */
    protected SelectBuilder(SelectBuilder other) {

        this.distinct = other.distinct;
        this.forUpdate = other.forUpdate;
        this.noWait = other.noWait;

        for (Object column : other.columns) {
            if (column instanceof SubSelectBuilder) {
                this.columns.add(((SubSelectBuilder) column).clone().build());
            } else {
                this.columns.add(column);
            }
        }

        this.tables.addAll(other.tables);
        this.joins.addAll(other.joins);
        this.leftJoins.addAll(other.leftJoins);
        this.wheres.addAll(other.wheres);
        this.groupBys.addAll(other.groupBys);
        this.havings.addAll(other.havings);

        for (SelectBuilder selectBuilder : other.unions) {
            this.unions.add(selectBuilder.clone());
        }

        this.unionClauses = this.unions.stream().map(u -> u.build()).collect(Collectors.toList());

        this.orderBys.addAll(other.orderBys);
    }

    @Override
    public SelectBuilder clone() {
        return new SelectBuilder(this);
    }

    /**
     * Alias for {@link #where(String)}.
     */
    public SelectBuilder and(String expr) {
        return where(expr);
    }

    public SelectBuilder column(String name) {
        columns.add(name);
        return this;
    }

    public SelectBuilder column(SubSelectBuilder subSelect) {
        columns.add(subSelect.build());
        return this;
    }

    public SelectBuilder column(String name, boolean groupBy) {
        columns.add(name);
        if (groupBy) {
            groupBys.add(name);
        }
        return this;
    }

    public SelectBuilder limit(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
        return this;
    }

    public SelectBuilder limit(int limit) {
        return limit(limit, 0);
    }

    public SelectBuilder distinct() {
        this.distinct = true;
        return this;
    }

    public SelectBuilder forUpdate() {
        forUpdate = true;
        return this;
    }

    public SelectBuilder from(String table) {
        tables.add(table);
        return this;
    }

    public List<SelectBuilder> getUnions() {
        return unions;
    }

    public List<String> getUnionClauses() {
        return unionClauses;
    }

    public SelectBuilder groupBy(String expr) {
        groupBys.add(expr);
        return this;
    }

    public SelectBuilder having(String expr) {
        havings.add(expr);
        return this;
    }

    public SelectBuilder join(String join) {
        joins.add(join);
        return this;
    }

    public SelectBuilder leftJoin(String join) {
        leftJoins.add(join);
        return this;
    }

    public SelectBuilder noWait() {
        if (!forUpdate) {
            throw new RuntimeException("noWait without forUpdate cannot be called");
        }
        noWait = true;
        return this;
    }

    public SelectBuilder orderBy(String name) {
        orderBys.add(name);
        return this;
    }

    /**
     * Adds an ORDER BY item with a direction indicator.
     *
     * @param name      Name of the column by which to sort.
     * @param ascending If true, specifies the direction "asc", otherwise, specifies
     *                  the direction "desc".
     */
    public SelectBuilder orderBy(String name, boolean ascending) {
        if (ascending) {
            orderBys.add(name + " asc");
        } else {
            orderBys.add(name + " desc");
        }
        return this;
    }

    /**
     * Adds a "union" select builder. The generated SQL will union this query
     * with the result of the main query. The provided builder must have the
     * same columns as the parent select builder and must not use "order by" or
     * "for update".
     */
    public SelectBuilder union(SelectBuilder unionBuilder) {
//        unions.add(unionBuilder);
        unionClauses.add(unionBuilder.build());

        return this;
    }

    public SelectBuilder where(String expr) {
        wheres.add(expr);
        return this;
    }

    @Override
    public String build() {
        StringBuilder stringBuilder = new StringBuilder("select ");

        if (distinct) {
            stringBuilder.append("distinct ");
        }

        if (columns.size() == 0) {
            stringBuilder.append("*");
        } else {
            appendList(stringBuilder, columns, "", ", ");
        }

        appendList(stringBuilder, tables, " from ", ", ");
        appendList(stringBuilder, joins, " join ", " join ");
        appendList(stringBuilder, leftJoins, " left join ", " left join ");
        appendList(stringBuilder, wheres, " where ", " and ");
        appendList(stringBuilder, groupBys, " group by ", ", ");
        appendList(stringBuilder, havings, " having ", " and ");
        appendList(stringBuilder, unionClauses, " union ", " union ");
        appendList(stringBuilder, orderBys, " order by ", ", ");

        if (forUpdate) {
            stringBuilder.append(" for update");
            if (noWait) {
                stringBuilder.append(" nowait");
            }
        }

        if (limit > 0)
            stringBuilder.append(" limit ").append(limit);
        if (offset > 0)
            stringBuilder.append(", ").append(offset);

        return stringBuilder.toString();
    }

}
