package com.phoenix.common.structure.imp;

import com.phoenix.common.exceptions.SupportException;
import com.phoenix.common.structure.Tuple;
import junit.framework.TestCase;

public class TriTupleImplTest extends TestCase {

    public void testCreate() throws SupportException {
        String[] exprs = {"a", "b", "c"};
        Class[] types = {String.class, Integer.class, String.class};

        Tuple tuple = new TriTupleImpl(exprs, types, "a", 1, "c");


        System.out.println(tuple.get(0, String.class));
        String tmp = tuple.get(0);
        System.out.println(tmp);
    }

}