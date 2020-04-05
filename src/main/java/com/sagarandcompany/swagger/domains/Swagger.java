
package com.sagarandcompany.swagger.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Swagger {

    @Builder.Default
    private String swagger = "2.0";
    private Info info = null;
    private String host = "swagger.io";
    private String basePath;

    private Set<Tag> tags = null;
    private Map<String, Map<String, PathDetail>> paths = null;
    private Map<String, ApiKey> securityDefinitions = null;
    private Map<String, ApiResponse> definitions = null;
    private ExternalDocs externalDocs = null;
    @Builder.Default
    private List<String> schemes = Arrays.asList("https", "http");
}
