package org.hehaoming.user.config.web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title("用户接口")
                        .contact(new Contact("小铭", "", "17512910006@163.com"))
                        .version("999.0")
                        .description("用户增删改查")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.hehaoming.user.controller"))
                .paths(PathSelectors.any())
                .build();

    }
}
