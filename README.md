# Swagger for Spring Cloud Function(Azure/AWS/GCP)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://www.youtube.com/channel/UCKLrvwglZb6h9SEM__fgSjg)

### 1)- How to install?
add below depedency in yout pom.xml or build.gradle
```xml
<dependency>
            <groupId>com.sagarandcompany.swagger</groupId>
            <artifactId>spring-boot-starter-cloudswagger</artifactId>
            <version>LATEST</version>
</dependency>
```
```gradle
compile 'com.sagarandcompany.swagger:spring-boot-starter-cloudswagger:LATEST'
```
### 2)-How to Override Custom Config for Swagger Documentation in properties file
```java
swagger.email=sagarmal624@gmail.com
swagger.description=Swagger API Documentation
swagger.licenseName=Apache
swagger.licenseUrl=http://www.apache.org/licenses/license-2.0
swagger.termsOfService=Swagger
swagger.title=Swagger API documentation
swagger.version=1.0
swagger.apiKeyName=Authorization
swagger.externalDocsDescription=Click Here for more info
swagger.externalDocsUrl=https://swagger.io/
```
```txt
// Don't remove com.sagarandcompany.swagger package
Add Your Package in below way
@SpringBootApplication(scanBasePackages = {"com.sagarandcompany.swagger", "com.example.custom.packagename"})
```

### 3)-What all are the annotation to use Swagger ?
```java
@ApiInfo
@ParameterInfo
@ExternalDocInfo
@ApiHeaders
```

### 4)-@ApiInfo Annotation
This annotation is used to specify your api endpoint information and its metadata
```java
@ApiInfo(path = "/api/user/profile/{id}", method = HttpMethod.GET, tagName = "UserProfile")
@ApiInfo(path = "/api/user/save", method = HttpMethod.POST, tagName = "UserSave")
@ApiInfo(path = "/api/user/delete/{id}", method = HttpMethod.DELETE, tagName = "UserDelete")
```
#### Explaination
Path: you need to put your api endpoint
method: what is http request method:
tagName: You need to tag your api with Meanningfull name. it should be unique
description: This is used to describe your api.

### 5)-@ParameterInfo
This is used to pass paramater into your api.Paramater you can pass in three way
1)Query String
2)Path Variable
3)Request Body

You can pass multiple @Params annotation because as we know we can have multiple params in different form.
```java
@ParameterInfo(parameters = {@Params(name = "id", in = SwaggerInType.PATH, required = true, dataType = "integer"),
})
@ParameterInfo(parameters = {@Params(name = "name", in = SwaggerInType.QUERY, required = true, dataType = "string")
})
@ParameterInfo(parameters = {@Params(name = "Save", in = SwaggerInType.BODY, required = true, dataType = "object", payloadClass = User.class)
})
```
#### Explaination
@Params: it is used to collect parameter info
name: This is a parameter name
in:  this is used to define your passing data method for your api's.it would be    QUERy,PATH,BODY
required: it defines parameter is mandatory or not.
dataType: what is the type of your data.  string|integer|object|long|double etc
payloadClass: This class is used for POST/PUT request method.Need to provide payload Class.


### 6)-@ExternalDocInfo annotation
This is used to define external document information
```java
@ExternalDocInfo(externalDocDescription = "For more info",externalDocUrl ="https://www.google.com/en/" )
```
### 7)- @ApiHeaders Annotation:
This is used to pass custom header in api.
You need to pass header name and required value. and you can pass multiple headers in api's

```java
@ApiHeaders(headers = @Header(name = "location-type",required = true))
```
