package com.phoenix.api.core.service;

import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.model.Model;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class TestJCasbinService {
    @Test
    public void testInitEnforcement() {
        Model model = new Model();

        initModel(model);
        initPolicies(model);

        Enforcer enforcer = new Enforcer(model);

        System.out.println(enforcer.enforce("alice", "data1", "read"));
    }

    //---------------------------------------------------------------------

    private void initModel(Model model) {
        model.addDef("r", "r", "sub, obj, act");
        model.addDef("p", "p", "sub, obj, act");
        model.addDef("g", "g", " _, _");
        model.addDef("g", "g2", " _, _");
        model.addDef("e", "e", "some(where (p.eft == allow))");
        model.addDef("m", "m", "g(r.sub, p.sub) && g2(r.obj, p.obj) && r.act == p.act");
    }

    private void initPolicies(Model model) {

        model.addPolicy("p", "p", getRule("alice", "data1", "read"));
        model.addPolicy("p", "p", getRule("bob", "data2", "write"));
        model.addPolicy("p", "p", getRule("data_group_admin", "data_group", "write"));

        model.addPolicy("g", "g", getRule("alice", "data_group_admin"));

        model.addPolicy("g", "g2", getRule("data1", "data_group"));
        model.addPolicy("g", "g2", getRule("data2", "data_group"));

    }

    private List<String> getRule(String... arg) {
        List<String> rules = new LinkedList<>();

        try {
            if (arg[0] != null && !arg[0].isEmpty()) {
                rules.add(arg[0]);
            }

            if (arg[1] != null && !arg[1].isEmpty()) {
                rules.add(arg[1]);
            }

            if (arg[2] != null && !arg[2].isEmpty()) {
                rules.add(arg[2]);
            }

            if (arg[3] != null && !arg[3].isEmpty()) {
                rules.add(arg[3]);
            }

            if (arg[4] != null && !arg[4].isEmpty()) {
                rules.add(arg[4]);
            }

            return rules;
        } catch (IndexOutOfBoundsException e) {
            return rules;
        }
    }
}
