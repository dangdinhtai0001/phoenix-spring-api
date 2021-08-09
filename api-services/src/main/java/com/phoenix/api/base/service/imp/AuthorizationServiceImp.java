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
import java.util.List;

@Service(BeanIds.AUTHORIZATION_SERVICES)
@Log4j2
public class AuthorizationServiceImp implements AuthorizationService {

    private final String POLICY_KEY = "p";
    private final String GROUP_POLICY_KEY = "g";

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
    public void loadPolicies(Enforcer enforcer) {
        try {
            List<CasbinRule> rules = authorizationRepository.findAllCasbinRules();

            for (CasbinRule rule : rules) {
                if (POLICY_KEY.equals(rule.getPType())) {
                    enforcer.addPolicy(rule.getArgumentsAsArray());
                }

                if (GROUP_POLICY_KEY.equals(rule.getPType())) {
                    enforcer.addGroupingPolicy(rule.getArgumentsAsArray());
                }
            }

        } catch (NoSuchFieldException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void clearPolicies(Enforcer enforcer) {
        enforcer.clearPolicy();
    }

    @Override
    public boolean enforce(Enforcer enforcer, Object... args) {
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
}
