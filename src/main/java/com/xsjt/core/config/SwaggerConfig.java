package com.xsjt.core.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SwaggerConfig
 * Swagger2配置文件
 * Date:     2021年10月21日 上午10:38:10
 * @author Harriss
 * @version
 */
@Configuration
public class SwaggerConfig {

    /**
     * 创建api文档.默认分组.
     * 创建多个bean实现创建多组文档.
     * @return
     */
    @Bean(value = "defaultApi")
    public Docket createRestApi(){
        //=====添加head参数start============================
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("auth").description("Token令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        // =========添加head参数end===================
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("Itwork-Api")
                .select()
                //控制层所在的包
                .apis(RequestHandlerSelectors.basePackage("com.xsjt.order.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ItWorks-API")
                .description("ItWorks 接口描述")
//                .termsOfServiceUrl("https://blog.csdn.net/qq_41107680")
//                .contact(new Contact("Harriss", "https://blog.csdn.net/qq_41107680", "2291544704@qq.com"))
                .version("1.0")
                .build();
    }
}
