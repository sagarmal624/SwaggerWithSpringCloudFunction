package com.sagarandcompany.swagger.service;

import java.util.Map;

public interface SwaggerUiService {
    public String getSwaggerUi(Map<String, String> appUrlMap);

    public Map<String, Object> getApiDocs(String basePackage);
}
