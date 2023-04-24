package top.goku.dailycost.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
import top.goku.dailycost.annotation.ApiController;

@Configuration
@EnableSwagger2WebMvc
@ConditionalOnProperty(name = "swagger.enable")
public class Knife4jConfiguration {

    @Bean
    public Docket mange() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
                        .description("# API接口")
                        .termsOfServiceUrl("")
                        .contact("")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("API接口")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.withClassAnnotation(ApiController.class))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
