package com.sagarandcompany.swagger.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerDocInfo {
    private String email;
    private String description;
    private String licenseName;
    private String licenseUrl;
    private String termsOfService;
    private String title;
    private String version;
    private String apiKeyName;
    private String externalDocsDescription;
    private String externalDocsUrl;
}
