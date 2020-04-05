
package com.sagarandcompany.swagger.domains;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ApiKey{
    @Builder.Default
    private String type="apiKey";
    @Builder.Default
    private String name="api_key";
    @Builder.Default
    private String in="header";


}
