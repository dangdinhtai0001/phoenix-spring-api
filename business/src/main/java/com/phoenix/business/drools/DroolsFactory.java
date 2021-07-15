/*
 * @Author: Đặng Đình Tài
 * @Created date: 7/15/21, 9:46 AM
 */

package com.phoenix.business.drools;

import com.phoenix.util.FileUtil;
import org.drools.decisiontable.DecisionTableProviderImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DroolsFactory {
    private static final String RULES_PATH = "drools/rules/";
    private static final String MAIN_DIR = "main";
    private static final String RESOURCES_DIR = "resources";
    private static final String SOURCE_DIR = "src";
    private final KieServices kieServices = KieServices.Factory.get();

    private KieFileSystem getKieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        List<String> rules = getAllPathsOfRule();
        for (String rule : rules) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(rule));
        }
        return kieFileSystem;
    }

    public KieContainer getKieContainer() throws IOException {
        getKieRepository();

        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
        kb.buildAll();

        KieModule kieModule = kb.getKieModule();

        return kieServices.newKieContainer(kieModule.getReleaseId());

    }

    private void getKieRepository() {
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
    }

    public KieSession getKieSession() {
        getKieRepository();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        List<String> rulePaths = getAllPathsOfRule();

        for (String rulePath : rulePaths) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(rulePath));
        }

        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();

        KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        return kContainer.newKieSession();

    }

    public KieSession getKieSession(Resource resource) {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem().write(resource);

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();

        KieRepository kieRepository = kieServices.getRepository();

        ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();

        KieContainer kieContainer = kieServices.newKieContainer(krDefaultReleaseId);

        return kieContainer.newKieSession();
    }

    /*
     * Can be used for debugging
     * Input excelFile example: drools/rules/Discount.xls
     */
    public String getDrlFromExcel(String excelFile) {
        DecisionTableConfiguration configuration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        configuration.setInputType(DecisionTableInputType.XLS);

        Resource resource = ResourceFactory.newClassPathResource(excelFile, getClass());

        DecisionTableProviderImpl decisionTableProvider = new DecisionTableProviderImpl();

        return decisionTableProvider.loadFromResource(resource, null);
    }

    private List<String> getAllPathsOfRule() {
        Path resourceDirectory = Paths.get(SOURCE_DIR, MAIN_DIR, RESOURCES_DIR, "drools", "rules");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        List<String> absolutePaths = FileUtil.getAllFilePathInDirectory(absolutePath);

        return absolutePaths.stream().map(this::getRulesPath).collect(Collectors.toList());
    }

    private String getRulesPath(String aPath) {
        Path resourceDirectory = Paths.get(SOURCE_DIR, MAIN_DIR, RESOURCES_DIR);
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        return aPath.substring(absolutePath.length() + 1);
    }
}
