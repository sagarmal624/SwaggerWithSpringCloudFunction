package com.sagarandcompany.swagger.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SwaggerHeader {
    private String name;
    private Boolean required;
}
