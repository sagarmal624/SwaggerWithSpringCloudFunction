package com.sagarandcompany.swagger.controller;

import com.sagarandcompany.swagger.domains.SwaggerInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
//@ApiInfo(path = "/api/user/save", method = HttpMethod.POST, tagName = "UserSave")
//@ParameterInfo(parameters = {@Params(name = "SaveLead Payload", in = SwaggerInType.BODY, required = true, dataType = "object", payloadClass = SwaggerInput.class)
//})
//@ExternalDocInfo(externalDocDescription = "For more info",externalDocUrl ="https://www.fwd.com/en/" )
public class TestController {
    @PostMapping("/sendEmail")
    public ResponseEntity sendEmail(@RequestBody SwaggerInput swaggerInput) {
        return ResponseEntity.ok(swaggerInput);
    }

//    @GetMapping("/name")
//    public ResponseEntity name() {
//        return ResponseEntity.ok(getFields(TestA.class));
//
//    }

}
