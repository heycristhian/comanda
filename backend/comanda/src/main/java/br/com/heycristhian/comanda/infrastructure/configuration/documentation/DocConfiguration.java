package br.com.heycristhian.comanda.infrastructure.configuration.documentation;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocConfiguration {

    private final String title;
    private final String description;
    private final String version;

    public DocConfiguration(@Value("${spring.application.swagger.title}") final String title,
                            @Value("${spring.application.swagger.description}") final String description,
                            @Value("${spring.application.swagger.version}") final String version) {
        this.title = title;
        this.description = description;
        this.version = version;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title(this.title)
                        .description(this.description)
                        .version(this.version)
                        .contact(new Contact()
                                .name("Cristhian Nunes Dias")
                                .email("heycristhian@gmail.com"))
                        .license(new License()
                                .name("Apache License Version 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

}
