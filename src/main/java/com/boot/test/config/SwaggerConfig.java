package com.boot.test.config;

import com.google.common.base.Predicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;
/**
 * Created by tao on 2017/2/14.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringfoxDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(demoApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.boot.test.controller"))
                .paths(PathSelectors.any())
                .build();
    }

//    @SuppressWarnings("unchecked")
//    @Bean
//    public Docket testApi(){
//        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//                .groupName("test")
//                .genericModelSubstitutes(DeferredResult.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
//                .select()
//                .paths(Predicates.or(PathSelectors.regex("/api/.*")))//过滤的接口
//                .build()
//                .apiInfo(testApiInfo());
//        ;
//        return docket;
//    }
//
//    @SuppressWarnings("unchecked")
//    @Bean
//    public Docket demoApi() {
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("demo")
//                .genericModelSubstitutes(DeferredResult.class)
////              .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(false)
//                .pathMapping("/")
//                .select()
//                .paths(Predicates.or(PathSelectors.regex("/demo/.*")))//过滤的接口
//                .build()
//                .apiInfo(demoApiInfo());
//    }
//
//    private ApiInfo testApiInfo() {
//        ApiInfo apiInfo = new ApiInfo("Test相关接口",//大标题
//                "Test相关接口，主要用于测试.",//小标题
//                "1.0",//版本
//                "http://www.taobao.com/",
//                new Contact("tao", "", ""),//作者
//                "xxxx信息科技有限公司",//链接显示文字
//                "http://www.baidu.com/"//网站链接
//        );
//
//        return apiInfo;
//    }

    private ApiInfo demoApiInfo() {
        ApiInfo apiInfo = new ApiInfo("Demo相关接口",//大标题
                "Demo相关接口，获取个数，获取列表，注意：",//小标题
                "1.0",//版本
                "http://www.taobao.com/",
                new Contact("tao", "", ""),//作者
                "xxxx信息科技有限公司",//链接显示文字
                "http://www.baidu.com/"//网站链接
        );

        return apiInfo;
    }

}
