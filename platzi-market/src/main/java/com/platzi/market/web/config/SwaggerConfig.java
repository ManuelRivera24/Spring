package com.platzi.market.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2) // Aqui se le dice el tipo de documentacion que se va a utilizar
                .select() // Le decimos que queremos que exponga en la documentacion (para eso usamos el metodo "select().apis()")
                .apis(RequestHandlerSelectors.basePackage("com.platzi.market.web.controller")) // Se le dice que solamente los que esten en el paquete "controller" queremos que sean expuestos a traves de la documentacion (porque en los controllers es donde tenemos los endpoints)
                .build(); // Esto para construir una respuesta
    }
}
