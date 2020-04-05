
package com.sagarandcompany.swagger.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Info {

    private String description;
    private String version;
    private String title;
    private String termsOfService;
    private Contact contact;
    private License license;



}
