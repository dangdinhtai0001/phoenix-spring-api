package com.phoenix.api.core.service;

import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.model.Model;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class TestJCasbinService {
    @Test
    public void testInitEnforcement() throws IOException {
        Model model = new Model();

        //initModel(model);
        initModelFromFile(model);
        initPolicies(model);

        Enforcer enforcer = new Enforcer(model);

        System.out.println(enforcer.enforce("alice", "data1", "read"));
    }

    @Test
    public void testReadModelFile() throws IOException {
        System.out.println(getModelContext());

    }

    @Test
    public void testRBACModel() throws IOException {
        Model model = new Model();
        String modelText = getModelText();

        model.loadModelFromText(modelText);

//        model.addPolicy("p", "p", getRule("TEACHER", "UserServiceImp", "countByCondition"));
//        model.addPolicy("g", "g", getRule("admin", "TEACHER"));
//        model.addPolicy("p", "p", getRule("alice", "data1", "read"));
//        model.addPolicy("p", "p", getRule("bob", "data2", "write"));
//        model.addPolicy("p", "p", getRule("data2_admin", "data2", "read"));
//        model.addPolicy("p", "p", getRule("data2_admin", "data2", "write"));
//        model.addPolicy("g", "g", getRule("alice", "data2_admin"));


        Enforcer enforcer = new Enforcer(model);

        enforcer.addPolicy("alice", "data1", "read");
        enforcer.addPolicy("bob", "data2", "write");
        enforcer.addPolicy("data2_admin", "data2", "read");
        enforcer.addPolicy("data2_admin", "data2", "write");
        enforcer.addGroupingPolicy("alice", "data2_admin");

        System.out.println(enforcer.getPermissionsForUser("alice"));

//        enforcer.enforce("admin", "UserServiceImp", "countByCondition");
//        enforcer.enforce("user", "UserServiceImp", "countByCondition");
        enforcer.enforce("alice", "data2", "read");
        enforcer.enforce("bob", "data2", "read");
    }

    //---------------------------------------------------------------------

    private String getModelText() throws IOException {
        return "[request_definition]\n" +
                "r = sub, obj, act\n" +
                "\n" +
                "[policy_definition]\n" +
                "p = sub, obj, act\n" +
                "\n" +
                "[role_definition]\n" +
                "g = _, _\n" +
                "\n" +
                "[policy_effect]\n" +
                "e = some(where (p.eft == allow))\n" +
                "\n" +
                "[matchers]\n" +
                "m = g(r.sub, p.sub) && r.obj == p.obj && r.act == p.act";
    }

    private void initModel(Model model) {
        model.addDef("r", "r", "sub, obj, act");
        model.addDef("p", "p", "sub, obj, act");
        model.addDef("g", "g", " _, _");
        model.addDef("g", "g2", " _, _");
        model.addDef("e", "e", "some(where (p.eft == allow))");
        model.addDef("m", "m", "g(r.sub, p.sub) && g2(r.obj, p.obj) && r.act == p.act");
    }

    private String getModelContext() throws IOException {
        File file = ResourceUtils.getFile("classpath:casbin/rbac_with_resource_roles_model.conf");
        InputStream inputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }


    private void initModelFromFile(Model model) throws IOException {
        String modelContext = getModelContext();
        model.loadModelFromText(modelContext);
//        model.loadModel("F:\\PROJECT\\Project2\\github\\phoenix-spring-api-v2\\api-services\\src\\main\\resources\\casbin\\rbac_model.conf");
    }

    private void initPolicies(Model model) {

        model.addPolicy("p", "p", getRule("alice", "data1", "read"));
        model.addPolicy("p", "p", getRule("bob", "data2", "write"));
        model.addPolicy("p", "p", getRule("data2_admin", "data2", "read"));
        model.addPolicy("p", "p", getRule("data2_admin", "data2", "write"));

        model.addPolicy("g", "g", getRule("alice", "data2_admin"));

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
