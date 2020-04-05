package com.sagarandcompany.swagger.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PathDetail {

    private List<String> tags = null;
    private String summary;
    private String description;
    private String operationId;
    @Builder.Default
    private List<String> consumes = Arrays.asList("application/json");
    @Builder.Default
    private List<String> produces = Arrays.asList("application/json");
    private List<Parameter> parameters = null;
    private Responses responses;

    private List<Map> security = null;
}
