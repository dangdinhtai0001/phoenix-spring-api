package com.phoenix.business.drools;

import com.phoenix.business.domain.Profile;
import org.junit.Test;
import org.kie.api.runtime.KieSession;

public class TestApplyRule {
    @Test
    public void applyTestRule4Teacher() {
        KieSession kieSession = new DroolsFactory().getKieSession();
        Profile profile = new Profile("name", "code", "MALE", "",
                "", "TEACHER");
        FilterConditional filterConditional = new FilterConditional();

        kieSession.setGlobal("condition", filterConditional);
        kieSession.insert(profile);
        kieSession.fireAllRules();

        System.out.println(filterConditional);
    }

    @Test
    public void applyTestRule4Student() {
        KieSession kieSession = new DroolsFactory().getKieSession();
        Profile profile = new Profile("name", "code", "MALE", "",
                "", "STUDENT");
        FilterConditional filterConditional = new FilterConditional();

        kieSession.setGlobal("condition", filterConditional);
        kieSession.insert(profile);
        kieSession.fireAllRules();

        System.out.println(filterConditional);
    }
}
