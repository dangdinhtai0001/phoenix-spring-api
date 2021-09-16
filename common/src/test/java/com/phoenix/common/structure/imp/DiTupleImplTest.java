package com.phoenix.common.structure.imp;


import com.phoenix.common.structure.Tuple;
import org.junit.Test;

public class DiTupleImplTest {

    @Test
    public void testCreateTuple() {
        String[] exprs = {"a", "b", "c"};
        Tuple tuple = new DiTupleImpl(exprs, "1", 2, "3", 4);

        System.out.println(tuple.size());
        System.out.println(tuple.get(3, Integer.class));
        System.out.println(tuple.get("a", String.class));
    }

}