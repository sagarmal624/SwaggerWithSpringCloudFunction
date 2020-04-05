
package com.sagarandcompany.swagger.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    @Builder.Default
    public String type="string";

}
