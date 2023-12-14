package com.example.work.config;
import io.swagger.models.auth.In;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Configuration
@EnableOpenApi
@ComponentScan("com.example.work.config*")
public class SwaggerConfiguration implements WebMvcConfigurer {
    @Resource
    private  SwaggerProperties swaggerProperties;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                //a prefix to the api base path
                .pathMapping("/")
                //是否开启 swagger
                .enable(swaggerProperties.getEnable())
                .host(swaggerProperties.getTryHost())
                // 授权信息设置，必要的header token等认证信息
                .securitySchemes(securitySchemes())
                // 授权信息全局应用
                .securityContexts(securityContexts())
                //支持的通信协议
                .protocols(new HashSet<>(Arrays.asList("http", "https")))
                // 选择哪些接口作为swagger的doc发布
                .select()
                //限定只包含指定注解标注的类
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                //限定只包含指定包下的类
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                //包含所有
//                .apis(RequestHandlerSelectors.any())
                //排除指定的 /user 路径下的所有子路径
//                .paths(PathSelectors.regex("/user/.*").negate())
//                .paths(PathSelectors.regex("/error").negate())
//                .paths(PathSelectors.regex("/actuator").negate())
//                .paths(PathSelectors.regex("/actuator/.*").negate())
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //联系人
                .contact(new Contact("Xiao Ming",null,"152xxxx4589@163.com"))
                .description(swaggerProperties.getApplicationDescription())
                .version("Application version:" + swaggerProperties.getApplicationVersion() + ",Spring Boot Version:" + SpringBootVersion.getVersion())
                .title(swaggerProperties.getApplicationName() + " Api Doc")
                .build();

    }

    public List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("BASE_TOKEN", "token", In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }

    public List<SecurityContext> securityContexts() {
        return Collections.singletonList(SecurityContext.builder().securityReferences(Collections.singletonList(new SecurityReference("BASE_TOKEN", new AuthorizationScope[]{new AuthorizationScope("global", "")}))).build());

    }

    /**
     * 通用拦截器排除swagger设置，所有拦截器都会自动加swagger相关的资源排除信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            Field registrationsField = FieldUtils.getField(InterceptorRegistry.class, "registrations", true);
            List<InterceptorRegistration> registrations = (List<InterceptorRegistration>) ReflectionUtils.getField(registrationsField, registry);
            if (registrations != null) {
                for (InterceptorRegistration interceptorRegistration : registrations) {
                    interceptorRegistration
                            .excludePathPatterns("/swagger**/**")
                            .excludePathPatterns("/webjars/**")
                            .excludePathPatterns("/v3/**")
                            .excludePathPatterns("/doc.html");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
