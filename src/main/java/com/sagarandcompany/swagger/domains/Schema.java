
package com.sagarandcompany.swagger.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Schema {

    @JsonProperty("$ref")
    private String ref;

    private String type;
}
