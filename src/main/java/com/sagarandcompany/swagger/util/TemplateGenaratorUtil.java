package com.sagarandcompany.swagger.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class TemplateGenaratorUtil {
    public String execute(String templateName,Map map) {
        Configuration configuration = prepareConfiguration();
        configuration.setClassForTemplateLoading(TemplateGenaratorUtil.class, "/templates");
        try {
            Template template = configuration.getTemplate(templateName.concat(".ftl"));
            StringWriter stringWriter = new StringWriter();
            template.process(prepareData(map), stringWriter);
            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    private Configuration prepareConfiguration() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        return configuration;
    }

    private Map<String, Object> prepareData(Map map) {
        Map<String,Object>model=new HashMap<>();
        if (map != null) {
            Iterator itMap = map.entrySet().iterator();
            while (itMap.hasNext()) {
                Map.Entry pair = (Map.Entry) itMap.next();
                model.put(pair.getKey().toString(), pair.getValue());
            }
        }
        return model;
    }
}