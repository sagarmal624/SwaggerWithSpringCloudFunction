
package com.sagarandcompany.swagger.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tag {
    private String name;
    private String description;
    private ExternalDocs externalDocs;
}
