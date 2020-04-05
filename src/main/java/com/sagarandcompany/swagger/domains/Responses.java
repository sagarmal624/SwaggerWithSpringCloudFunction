
package com.sagarandcompany.swagger.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Responses {

    @JsonProperty("200")
    private _200 _200;
    @JsonProperty("405")
    private _405 _405;
    @JsonProperty("404")
    private _404 _404;
    @JsonProperty("400")
    private _400 _400;
    @JsonProperty("500")
    private _500 _500;
    @JsonProperty("401")
    private _401 _401;
}
