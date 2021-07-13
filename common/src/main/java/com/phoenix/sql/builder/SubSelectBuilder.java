/*
 * @Date created: 7/12/21, 8:54 PM
 * @Last-Modified: 7/12/21, 8:54 PM
 * @Username: Admin
 * @Author: Đặng Đình Tài
 */

package com.phoenix.sql.builder;

/**
 * SelectBuilder that can be used as a sub-select in a column expression or FROM clause.
 */
public class SubSelectBuilder extends SelectBuilder {
    private final String alias;

    public SubSelectBuilder(String alias) {
        this.alias = alias;
    }

    protected SubSelectBuilder(SubSelectBuilder other) {
        super(other);
        this.alias = other.alias;
    }

    @Override
    public SubSelectBuilder clone() {
        return new SubSelectBuilder(this);
    }


    @Override
    public String build() {
        return "(" + super.build() + ") as " + alias;
    }
}
