package com.sagarandcompany.swagger.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sagarandcompany.swagger.annotation.*;
import com.sagarandcompany.swagger.domains.*;
import com.sagarandcompany.swagger.util.DataGenerator;
import com.sagarandcompany.swagger.util.SwaggerInType;
import com.sagarandcompany.swagger.util.TemplateGenaratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.*;

/*
 * Developer: Sagar Mal Shankhala
 * Email: Sagarmal624@gmail.com
 *
 *
 */
public class SwaggerUiServiceImpl implements SwaggerUiService {

    @Autowired
    TemplateGenaratorUtil templateGenaratorUtil;

    @Autowired
    DataGenerator dataGenerator;

    public String getSwaggerUi(Map<String, String> appUrlMap) {
        Map<String, Object> map = new HashMap<>();
        map.put("appList", appUrlMap);
        return templateGenaratorUtil.execute("index", map);
    }

    public Map<String, Object> getApiDocs(String basePackage) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Swagger swagger = dataGenerator.swagger(getSwaggerList(basePackage));
            return mapper.convertValue(swagger, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public SwaggerInput swaggerInput(ApiInfo apiInfo, ExternalDocInfo externalDocInfo, ParameterInfo parameterInfo, ApiHeaders apiHeaders) {
        SwaggerInput swaggerInput = SwaggerInput.builder()
                .summary(Objects.nonNull(apiInfo) ? apiInfo.description() : null)
                .description(Objects.nonNull(apiInfo) ? apiInfo.description() : null)
                .tagName(Objects.nonNull(apiInfo) ? apiInfo.tagName() : null)
                .method(Objects.nonNull(apiInfo) ? apiInfo.method().name().toLowerCase() : null)
                .path(Objects.nonNull(apiInfo) ? apiInfo.path() : null)
                .externalDocDescription(Objects.nonNull(externalDocInfo) ? externalDocInfo.externalDocDescription() : null)
                .externalDocUrl(Objects.nonNull(externalDocInfo) ? externalDocInfo.externalDocUrl() : null)
                .build();
        parameters(parameterInfo, apiHeaders, swaggerInput);
        return swaggerInput;
    }

    private void parameters(ParameterInfo parameterInfo, ApiHeaders apiHeaders, SwaggerInput swaggerInput) {
        List<Parameter> parameters = new ArrayList<>();
        Map<String, ApiResponse> definations = new HashMap<>();
        if (Objects.nonNull(parameterInfo)) {
            Params[] params = parameterInfo.parameters();
            for (Params param : params) {
                String type = null;
                if (!param.dataType().equals("object")) {
                    type = param.dataType();
                }
                parameters.add(Parameter.builder()
                        .type(type)
                        .required(param.required())
                        .name(param.name())
                        .in(param.in().getName())
                        .schema(schema(param))
                        .description(param.description())
                        .build());
                if (Objects.nonNull(param.payloadClass())) {
                    definations.put(dataGenerator.className(param.payloadClass()), definitions(param.payloadClass()));
                }

            }
        }
        List<Parameter> headers = apiheaders(apiHeaders);
        if (!CollectionUtils.isEmpty(headers)) {
            parameters.addAll(headers);
        }
        swaggerInput.setParameters(parameters);
        swaggerInput.setDefinitions(definations);
    }

    public ApiResponse definitions(Class clazz) {
        return ApiResponse
                .builder()
                .properties(properties(clazz))
                .build();
    }

    private Map<String, Type> properties(Class clazz) {
        Map<String, Type> properties = new HashMap<>();
        if (clazz != null) {
            for (ClassField classField : dataGenerator.getFields(clazz)) {
                properties.put(classField.getName(), Type.builder().type(classField.getType()).build());
            }
        }
        return properties;
    }

    private Schema schema(Params param) {
        Schema schema = null;
//        if (param.in() == SwaggerInType.QUERY || param.in() == SwaggerInType.PATH) {
//            schema = Schema.builder().type(param.dataType()).build();
//        } else
        if (Objects.nonNull(param.payloadClass()) && !dataGenerator.isInbuildClass(param.payloadClass().getName())) {
            schema = Schema.builder().ref("#/definitions/" + dataGenerator.className(param.payloadClass())).build();
        }
        return schema;
    }

    private List<Parameter> apiheaders(ApiHeaders apiHeaders) {
        if (Objects.nonNull(apiHeaders)) {
            Header[] headers = apiHeaders.headers();
            if (Objects.nonNull(headers)) {
                List<Parameter> parameters = new ArrayList<>();
                for (Header header : headers) {
                    parameters.add(Parameter.builder()
                            .type("string")
                            .required(header.required())
                            .name(header.name())
                            .in(SwaggerInType.HEADER.getName())
                            .build());
                }
                return parameters;
            }
        }
        return null;
    }

    private List<SwaggerInput> getSwaggerList(String scanPackage) {
        List<SwaggerInput> swaggerInputList = new ArrayList<>();
        ClassPathScanningCandidateComponentProvider apiInfoProvider = createComponentScanner(ApiInfo.class, ExternalDocInfo.class, ParameterInfo.class, ApiHeaders.class);
        for (BeanDefinition beanDef : apiInfoProvider.findCandidateComponents(scanPackage)) {
            Map<String, Annotation> annotationMap = apiInfo(beanDef);
            ApiInfo apiInfo = (ApiInfo) annotationMap.get("ApiInfo");
            ExternalDocInfo externalDocInfo = (ExternalDocInfo) annotationMap.get("ExternalDocInfo");
            ParameterInfo parameterInfo = (ParameterInfo) annotationMap.get("ParameterInfo");
            ApiHeaders apiHeaders = (ApiHeaders) annotationMap.get("ApiHeaders");
            swaggerInputList.add(swaggerInput(apiInfo, externalDocInfo, parameterInfo, apiHeaders));
        }
        return swaggerInputList;
    }

    private ClassPathScanningCandidateComponentProvider createComponentScanner(Class apiIInfo, Class exteralDoc, Class paramInfo, Class apiHeaders) {
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(apiIInfo));
        provider.addIncludeFilter(new AnnotationTypeFilter(exteralDoc));
        provider.addIncludeFilter(new AnnotationTypeFilter(paramInfo));
        provider.addIncludeFilter(new AnnotationTypeFilter(apiHeaders));
        return provider;
    }

    private Map<String, Annotation> apiInfo(BeanDefinition beanDef) {
        Map<String, Annotation> annotationMap = new HashMap<>();
        try {
            Class<?> cl = Class.forName(beanDef.getBeanClassName());
            annotationMap.put("ApiInfo", cl.getAnnotation(ApiInfo.class));
            annotationMap.put("ExternalDocInfo", cl.getAnnotation(ExternalDocInfo.class));
            annotationMap.put("ParameterInfo", cl.getAnnotation(ParameterInfo.class));
            annotationMap.put("ApiHeaders", cl.getAnnotation(ApiHeaders.class));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Got exception: " + e.getMessage());
        }
        return annotationMap;
    }

}
