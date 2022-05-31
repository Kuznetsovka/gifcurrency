//package ru.kuznetsovka.gifcurrency.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.builders.ResponseBuilder;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.time.LocalDate;
//
//import static java.util.Collections.singletonList;
//
//@Configuration
//public class SpringFoxConfig {
//    @Bean
//    public Docket petApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("ru.kuznetsovka.gifcurrency"))
//                .paths(PathSelectors.any())
//                .build()
//                .pathMapping("/api/v1")
//                .directModelSubstitute(LocalDate.class, String.class)
//                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .globalResponses(HttpMethod.GET,
//                        singletonList(new ResponseBuilder()
//                                .code("500")
//                                .description("500 message")
//                                .representation(MediaType.TEXT_XML)
//                                .apply(r ->
//                                        r.model(m ->
//                                                m.referenceModel(ref ->
//                                                        ref.key(k ->
//                                                                k.qualifiedModelName(q ->
//                                                                        q.namespace("some:namespace")
//                                                                                .name("ERROR"))))))
//                                .build()));
//    }
//}