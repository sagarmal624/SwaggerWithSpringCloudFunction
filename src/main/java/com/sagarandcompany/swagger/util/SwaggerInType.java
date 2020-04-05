package com.sagarandcompany.swagger.util;

public enum SwaggerInType {
    PATH("path"), BODY("body"),QUERY("query"),HEADER("header");
    String name;

    SwaggerInType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
