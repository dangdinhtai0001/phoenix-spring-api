package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.model.CasbinRule;
import com.phoenix.api.base.repositories.AuthorizationRepository;
import com.phoenix.api.base.service.AuthorizationService;
import lombok.extern.log4j.Log4j2;
import org.casbin.jcasbin.main.Enforcer;
import org.casbin.jcasbin.model.Model;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

@Service(BeanIds.AUTHORIZATION_SERVICES)
@Log4j2
public class AuthorizationServiceImp implements AuthorizationService {

    private final AuthorizationRepository authorizationRepository;

    public AuthorizationServiceImp(
            @Qualifier(BeanIds.AUTHORIZATION_REPOSITORY_IMP) AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public Model loadModelFromPath(String path) {
        Model model = new Model();

        try {
            String modelText = getModelTextFromFilePath(path);
            model.loadModelFromText(modelText);
        } catch (IOException exception) {
            log.warn(exception.getMessage(), exception);

            // Nếu có lỗi thì dùng model mặc định

            model.addDef("r", "r", "sub, obj, act");
            model.addDef("p", "p", "sub, obj, act");
            model.addDef("g", "g", " _, _");
            model.addDef("e", "e", "some(where (p.eft == allow))");
            model.addDef("m", "m", "g(r.sub, p.sub) && r.obj == p.obj && r.act == p.act");
        }

        return model;
    }

    @Override
    public void loadPolicies(Model model) {
        try {
            List<CasbinRule> rules = authorizationRepository.findAllCasbinRules();

            for (CasbinRule rule : rules) {
                model.addPolicy(rule.getPType(), rule.getPType(), rule.getArguments());
            }
        } catch (NoSuchFieldException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void clearPolicies(Model model) {
        model.clearPolicy();
    }

    @Override
    public boolean enforce(Model model, Object... args) {
        Enforcer enforcer = new Enforcer(model);

        return enforcer.enforce(args);
    }


    //--------------------------------------------------------------
    //-------
    //--------------------------------------------------------------

    private String getModelTextFromFilePath(String path) throws IOException {
        File file = ResourceUtils.getFile(path);
        InputStream inputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    private List<String> getRule(String... args) {
        List<String> rules = new LinkedList<>();


        if (args[0] != null && !args[0].isEmpty()) {
            rules.add(args[0]);
        }

        if (args[1] != null && !args[1].isEmpty()) {
            rules.add(args[1]);
        }

        if (args[2] != null && !args[2].isEmpty()) {
            rules.add(args[2]);
        }

        if (args.length > 3 && args[3] != null && !args[3].isEmpty()) {
            rules.add(args[3]);
        }

        if (args.length > 4 && args[4] != null && !args[4].isEmpty()) {
            rules.add(args[4]);
        }

        return rules;

    }


}
