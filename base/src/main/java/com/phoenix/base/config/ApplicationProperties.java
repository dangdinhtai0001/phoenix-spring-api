package com.phoenix.base.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class ApplicationProperties {

    private Path applicationPath;
    private Version applicationVersion;
    private Jwt applicationJWT;
    private Jwt applicationAuthorization;

    @Getter
    @Setter
    public static class Path {
        private String prefix;
    }

    @Getter
    @Setter
    public static class Version {
        private String build;
        private String service;
    }

    @Getter
    @Setter
    public static class Jwt {
        private String secret;
        private long expired;
    }

    @Getter
    @Setter
    public static class Authorization {
        private String modelPath;
    }


}

