package com.sagarandcompany.swagger.config;

import com.sagarandcompany.swagger.service.SwaggerDocInfo;
import com.sagarandcompany.swagger.service.SwaggerUiService;
import com.sagarandcompany.swagger.service.SwaggerUiServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConditionalOnClass({SwaggerUiService.class, SwaggerDocInfo.class})
@Component
public class SwaggerConfig {
    @Value("${swagger.email}")
    private String email;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.licenseName}")
    private String licenseName;

    @Value("${swagger.licenseUrl}")
    private String licenseUrl;

    @Value("${swagger.termsOfService}")
    private String termsOfService;

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.version}")
    private String version;

    @Value("${swagger.apiKeyName}")
    private String apiKeyName;

    @Value("${swagger.externalDocsDescription}")
    private String externalDocsDescription;

    @Value("${swagger.externalDocsUrl}")
    private String externalDocsUrl;

    @Bean
    @ConditionalOnMissingBean
    public SwaggerUiService swaggerUiService() {
        return new SwaggerUiServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public SwaggerDocInfo swaggerDocInfoConfig() {
        SwaggerDocInfo swaggerDocInfo = new SwaggerDocInfo();
        swaggerDocInfo.setApiKeyName(apiKeyName);
        swaggerDocInfo.setDescription(description);
        swaggerDocInfo.setEmail(email);
        swaggerDocInfo.setExternalDocsDescription(externalDocsDescription);
        swaggerDocInfo.setExternalDocsUrl(externalDocsUrl);
        swaggerDocInfo.setTitle(title);
        swaggerDocInfo.setVersion(version);
        swaggerDocInfo.setTermsOfService(termsOfService);
        swaggerDocInfo.setLicenseName(licenseName);
        swaggerDocInfo.setLicenseUrl(licenseUrl);
        return swaggerDocInfo;
    }
}
