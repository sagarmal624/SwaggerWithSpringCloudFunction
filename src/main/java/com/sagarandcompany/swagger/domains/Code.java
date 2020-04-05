
package com.sagarandcompany.swagger.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Code {
    @Builder.Default
    public String type = "integer";
    @Builder.Default
    public String format = "int32";
}
