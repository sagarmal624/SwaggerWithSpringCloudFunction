
package com.sagarandcompany.swagger.domains;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ApiResponse {
    @Builder.Default
    public String type = "object";
    public Map<String,Type> properties;

}
