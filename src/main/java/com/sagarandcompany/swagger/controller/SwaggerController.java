package com.sagarandcompany.swagger.controller;

import com.sagarandcompany.swagger.annotation.ExternalDocInfo;
import com.sagarandcompany.swagger.service.SwaggerUiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
//@ApiInfo(path = "/api/user/get", method = HttpMethod.GET, tagName = "UserGet")
//@ParameterInfo(parameters = {@Params(name = "name", in = SwaggerInType.QUERY, required = true, dataType = "string")
//})
@ExternalDocInfo(externalDocDescription = "For more info", externalDocUrl = "https://www.fwd.com/en/")
public class SwaggerController {
    @Autowired
    SwaggerUiService swaggerUiService;

    @GetMapping("/swagger/ui")
    @ResponseBody
    public String ui() {
        Map<String, String> map = new HashMap<>();
        map.put("Notification", "https://petstore.swagger.io/v2/swagger.json");
        map.put("Search", "http://localhost:7071/api/");
        map.put("Policy", "www.google.com");
        return swaggerUiService.getSwaggerUi(map);
    }

    @GetMapping("/api/json")
    @ResponseBody
    public Map apiJson() throws IOException {
        return swaggerUiService.getApiDocs("com.sapient.pscode.controller");
    }

}
