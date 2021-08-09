/*
 * @Date created: 7/12/21, 9:24 PM
 * @Last-Modified: 7/12/21, 9:24 PM
 * @Username: Admin
 * @Author: Đặng Đình Tài
 */

package com.phoenix.sql.builder;

import com.phoenix.common.builder.SelectBuilder;
import com.phoenix.common.builder.SubSelectBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSelectBuilder {
    @Test
    public void testBasics() {
        SelectBuilder selectBuilder = new SelectBuilder("Employee");
        assertEquals("select * from Employee", selectBuilder.build());

        selectBuilder = new SelectBuilder("Employee e");
        assertEquals("select * from Employee e", selectBuilder.build());

        selectBuilder = new SelectBuilder("Employee e").column("name");
        assertEquals("select name from Employee e", selectBuilder.build());

        selectBuilder = new SelectBuilder("Employee e").column("name").column("age");
        assertEquals("select name, age from Employee e", selectBuilder.build());

        selectBuilder = new SelectBuilder("Employee e").column("name as n").column("age");
        assertEquals("select name as n, age from Employee e", selectBuilder.build());

        //
        // Where clauses
        //

        selectBuilder = new SelectBuilder("Employee e").where("name like 'Bob%'");
        assertEquals("select * from Employee e where name like 'Bob%'", selectBuilder.build());

        selectBuilder = new SelectBuilder("Employee e").where("name like 'Bob%'").where("age > 37");
        assertEquals("select * from Employee e where name like 'Bob%' and age > 37", selectBuilder.build());

        //
        // Join clauses
        //

        selectBuilder = new SelectBuilder("Employee e").join("Department d on e.dept_id = d.id");
        assertEquals("select * from Employee e join Department d on e.dept_id = d.id", selectBuilder.build());

        selectBuilder = new SelectBuilder("Employee e").join("Department d on e.dept_id = d.id").where("name like 'Bob%'");
        assertEquals("select * from Employee e join Department d on e.dept_id = d.id where name like 'Bob%'", selectBuilder
                .build());

        //
        // Order by clauses
        //

        selectBuilder = new SelectBuilder("Employee e").orderBy("name");
        assertEquals("select * from Employee e order by name", selectBuilder.build());

        selectBuilder = new SelectBuilder("Employee e").orderBy("name desc").orderBy("age");
        assertEquals("select * from Employee e order by name desc, age", selectBuilder.build());

        selectBuilder = new SelectBuilder("Employee").where("name like 'Bob%'").orderBy("age");
        assertEquals("select * from Employee where name like 'Bob%' order by age", selectBuilder.build());

        //
        // For Update
        //

        selectBuilder = new SelectBuilder("Employee").where("id = 42").forUpdate();
        assertEquals("select * from Employee where id = 42 for update", selectBuilder.build());

    }

    @Test
    public void testLimits() {
        SelectBuilder selectBuilder = new SelectBuilder()
                .from("test_table")
                .column("a")
                .column("b")
                .limit(10);
        assertEquals("select a, b from test_table limit 10", selectBuilder.build());

        selectBuilder = selectBuilder.limit(10, 4);
        assertEquals("select a, b from test_table limit 10, 4", selectBuilder.build());
    }

    @Test
    public void testUnions() {
        SelectBuilder selectBuilder = new SelectBuilder()
                .column("a")
                .column("b")
                .from("Foo")
                .where("a > 10")
                .orderBy("1");

        selectBuilder.union(new SelectBuilder()
                .column("c")
                .column("d")
                .from("Bar"));

        assertEquals("select a, b from Foo where a > 10 union select c, d from Bar order by 1", selectBuilder.build());

    }

    @Test
    public void testSubQuery() {
        SelectBuilder selectBuilder = new SelectBuilder()
                .column("a.col_name")
                .column("b")
                .from("Foo")
                .where(new SubSelectBuilder("aa").column("a").where("a > 0").build())
                .where("a > 10")
                .orderBy("1");


        System.out.println(selectBuilder.build());


    }
}
