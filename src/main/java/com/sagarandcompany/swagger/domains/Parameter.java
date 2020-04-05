
package com.sagarandcompany.swagger.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Parameter {
    private String name;
    private String in;
    private String description;
    @Builder.Default
    private Boolean required = false;
    private Schema schema;
    private String type;
}
