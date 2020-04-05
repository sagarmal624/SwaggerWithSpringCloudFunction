package com.sagarandcompany.swagger.domains;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class SwaggerInput {
    private String path;
    private String method;
    private String tagName;
    private String summary;
    private String description;
    private String apiKeyName;
    private String externalDocDescription;
    private String externalDocUrl;
    private List<Parameter> parameters;
    private List<SwaggerHeader> headers;
    private Map<String, ApiResponse> definitions = null;

}
