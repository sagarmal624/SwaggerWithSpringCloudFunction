package com.sagarandcompany.swagger.util;

import com.sagarandcompany.swagger.domains.*;
import com.sagarandcompany.swagger.service.SwaggerDocInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataGenerator {
    @Autowired
    SwaggerDocInfo swaggerDocInfo;

    public Info info() {
        return Info.builder()
                .contact(Contact.builder().email(swaggerDocInfo.getEmail()).build())
                .description(swaggerDocInfo.getDescription())
                .license(License.builder().name(swaggerDocInfo.getLicenseName()).url(swaggerDocInfo.getLicenseUrl()).build())
                .termsOfService(swaggerDocInfo.getTermsOfService()).title(swaggerDocInfo.getTitle()).version(swaggerDocInfo.getVersion())
                .build();
    }

    public Map<String, Map<String, PathDetail>> paths(List<SwaggerInput> swaggerInputList) {
        Map<String, Map<String, PathDetail>> map = new HashMap<>();
        for (SwaggerInput swaggerInput : swaggerInputList) {
            Map<String, PathDetail> pathDetailMap = new HashMap<>();
            pathDetailMap.put(swaggerInput.getMethod(), pathDetail(swaggerInput));
            map.put(swaggerInput.getPath(), pathDetailMap);
        }
        return map;
    }

    public Swagger swagger(List<SwaggerInput> swaggerInputList) {
        return Swagger.builder()
                .paths(paths(swaggerInputList))
                .tags(tagSet(swaggerInputList))
                .securityDefinitions(securityDefinitions())
                .info(info())
                .externalDocs(externalDocs(null, "Swagger"))
                .definitions(definitions(swaggerInputList))
                .build();
    }

    public Map<String, ApiKey> securityDefinitions() {
        Map<String, ApiKey> map = new HashMap<>();
        map.put(swaggerDocInfo.getApiKeyName(), ApiKey
                .builder()
                .name(swaggerDocInfo.getApiKeyName())
                .build());
        return map;
    }

    private String apiKey(SwaggerInput swaggerInput) {
        String keyName = swaggerInput.getApiKeyName();
        if (StringUtils.isEmpty(keyName)) {
            keyName = swaggerDocInfo.getApiKeyName();
        }
        return keyName;
    }

    private Set<Tag> tagSet(List<SwaggerInput> swaggerInputList) {
        Set<Tag> tags = new HashSet<>();
        for (SwaggerInput swaggerInput : swaggerInputList) {
            tags.add(Tag.builder().
                    name(swaggerInput.getTagName()).
                    externalDocs(externalDocs(swaggerInput, "Tag")).
                    description(swaggerInput.getDescription()).
                    build());
        }
        return tags;
    }

    private PathDetail pathDetail(SwaggerInput swaggerInput) {
        return PathDetail.builder()
                .tags(Arrays.asList(swaggerInput.getTagName()))
                .parameters(swaggerInput.getParameters())
                .operationId(swaggerInput.getMethod().concat(swaggerInput.getTagName()))
                .summary(swaggerInput.getSummary())
                .description(swaggerInput.getDescription())
                .responses(response())
                .security(security(swaggerInput))
                .build();
    }


    public ExternalDocs externalDocs(SwaggerInput swaggerInput, String type) {
        String desc = null;
        String url = null;
        if (type.equals("Tag")) {
            desc = swaggerInput.getExternalDocDescription();
            url = swaggerInput.getExternalDocUrl();
        } else if (type.equals("Swagger")) {
            desc = swaggerDocInfo.getDescription();
            url = swaggerDocInfo.getExternalDocsUrl();
        }
        return ExternalDocs.builder()
                .description(desc)
                .url(url)
                .build();
    }


    public Map<String, ApiResponse> definitions(List<SwaggerInput> swaggerInputs) {
        Map<String, ApiResponse> map = new HashMap<>();
        for (SwaggerInput swaggerInput : swaggerInputs) {
            if (swaggerInput.getDefinitions() != null) {
                map.putAll(swaggerInput.getDefinitions());
            }
        }
        return map;
    }

    public <T> String className(T t) {
        if (Objects.nonNull(t)) {
            String name = ((Class) t).getName();
            return name.substring(name.lastIndexOf('.') + 1);
        }
        return "";
    }

    private List<Map> security(SwaggerInput swaggerInput) {
        Map<String, List> map = new HashMap<>();
        String key = apiKey(swaggerInput);
        map.put(key, Arrays.asList(key));
        return Arrays.asList(map);
    }

    private Responses response() {
        return Responses.builder()
                ._200(_200.builder().build())
                ._401(_401.builder().build())
                ._405(_405.builder().build())
                ._404(_404.builder().build())
                ._400(_400.builder().build())
                ._500(_500.builder().build())
                .build();
    }

    public boolean isInbuildClass(String classname) {
        return classname.startsWith("java.lang");
    }

    public <T> List<ClassField> getFields(T t) {
        List<Field> fields = new ArrayList<>();
        Class clazz = (Class) t;
        while (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields.stream().map(it ->
                ClassField.builder()
                        .name(it.getName())
                        .type((isInbuildClass(it.getType().getName()) ? className(it.getType()) : "object").toLowerCase())
                        .build()
        ).collect(Collectors.toList());
    }
}
