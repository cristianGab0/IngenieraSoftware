package com.gt.umg.ing.software.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 *
 * @author Cristian
 */


@Configuration
@EnableSwagger2
//@EnableWebMvc
public class SwaggerConfig /*implements WebMvcConfigurer */{

    @Bean
    public Docket produceApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.gt.umg.ing.software.controller"))
                .build();
    }
//    
//     @Override
//    public void addCorsMappings(CorsRegistry corsRegistry) {
//        corsRegistry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("*")
//                .maxAge(3600L)
//                .allowedHeaders("*")
//                .allowCredentials(false);
//    }
// 
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/swagger-ui/**");
// 
//    }
}

