package Gestion.Tareas.Infolaft.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Management API")
                        .version("1.0")
                        .description("API para gestión de tareas - Prueba Técnica")
                        .contact(new Contact()
                                .name("ANDERSONS AREVALO MADRID")
                                .email("arevalomadrid62776@gmail.com")));
    }

}
