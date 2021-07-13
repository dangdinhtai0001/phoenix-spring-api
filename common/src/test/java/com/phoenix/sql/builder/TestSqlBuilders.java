/*
 * @Date created: 7/12/21, 8:47 PM
 * @Last-Modified: 7/12/21, 8:47 PM
 * @Username: Admin
 * @Author: Đặng Đình Tài
 */

package com.phoenix.sql.builder;

import org.junit.Test;

public class TestSqlBuilders {
    @Test
    public void testCreateInsertQuery() {
        String sql = new InsertBuilder("Table_name")
                .set("col_0", "value_0")
                .build();

        System.out.println(sql);
    }

    @Test
    public void testCreateSelectQuery() {
        String sql = new SelectBuilder()
                .column("name")
                .column("age")
                .from("Employee")
                .where("dept = 'engineering'")
                .where("salary > 100000")
                .build();

        System.out.println(sql);
    }

    @Test
    public void testCreateSelectQueryWithSubQuery() {
        SubSelectBuilder subQuery = new SubSelectBuilder("alias");

        String sql = new SelectBuilder(subQuery)
                .column("name")
                .column("age")
                .from("Employee")
                .where("dept = 'engineering'")
                .where("salary > 100000")
                .build();

        System.out.println(sql);
    }
}
