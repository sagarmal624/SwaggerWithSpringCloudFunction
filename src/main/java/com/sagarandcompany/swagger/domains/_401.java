
package com.sagarandcompany.swagger.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class _401 {
    @Builder.Default
    private String description = "Unauthorized Request";

}
