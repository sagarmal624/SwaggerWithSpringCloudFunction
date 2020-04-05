
package com.sagarandcompany.swagger.domains;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Properties {
    @Builder.Default
    public Code code = Code.builder().build();
    @Builder.Default
    public Type type = Type.builder().build();
    @Builder.Default
    public Message message = Message.builder().build();
}
