package com.sagarandcompany.swagger.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassField {
    private String name;
    private String type;
}
