package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.service.AuthorizationService;
import lombok.extern.log4j.Log4j2;
import org.casbin.jcasbin.model.Model;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;

@Service(BeanIds.AUTHORIZATION_SERVICES)
@Log4j2
public class AuthorizationServiceImp implements AuthorizationService {
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

    }

    @Override
    public boolean enforce(Model model, String... args) {
        return false;
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
