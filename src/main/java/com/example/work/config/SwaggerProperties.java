package com.example.work.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    private Boolean enable;
    private String applicationName;
    private String applicationVersion;
    private String applicationDescription;
    private String tryHost;
}