/*
 * @Author: Đặng Đình Tài
 * @Created date: 7/15/21, 9:48 AM
 */

package com.phoenix.business.drools;

import com.phoenix.util.FileUtil;
import org.junit.Test;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TestDroolsFactory {

    @Test
    public void testLoadRuleFile() throws IOException {
        String resourceName = "drools/rules";

        Path resourceDirectory = Paths.get("src", "test", "resources", "drools", "rules");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        System.out.println(absolutePath);


        List<String> list = FileUtil.getAllFilePathInDirectory(absolutePath);
        System.out.println(list);

        List<String> rules = list.stream().map(this::getRulesPath).collect(Collectors.toList());
        System.out.println(rules);
    }

    @Test
    public void testCreateKieSession(){
        Resource resource = ResourceFactory.newClassPathResource("drools\\rules\\group_rules_0\\rule_config_0.drl", getClass());
        KieSession  kieSession = new DroolsFactory().getKieSession(resource);
    }


    @Test
    public void testGetKieSession(){
        KieSession  kieSession = new DroolsFactory().getKieSession();
        System.out.println(kieSession);
    }

    public String getRulesPath(String aPath) {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        return aPath.substring(absolutePath.length()+1);
    }


}
