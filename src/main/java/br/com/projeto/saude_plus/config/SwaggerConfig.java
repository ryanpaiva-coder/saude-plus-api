package br.com.projeto.saude_plus.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
            .info(
                new Info()
                    .title("Saude Plus API")
                    .version("1.0")
                    .description(
                        "API para marcar e conferir consultas médicas.\n\n" +
                        "---\n" +
                        "**Universidade Potiguar - UNP**  \n" +
                        "**Modelagem de Software**  \n" +
                        "**Autores:**\n" +
                        "- Alexandre Henrique Fernandes da Silva [alexandrehenriquefdes@gmail.com](mailto:alexandrehenriquefdes@gmail.com)\n" +
                        "- Eric Rafael da Silva Souza [rafael.souza29184@gmail.com](mailto:rafael.souza29184@gmail.com)\n" +
                        "- João Victor Santos da Costa [joaovictorsdc07@gmail.com](mailto:joaovictorsdc07@gmail.com)\n" +
                        "- Ryan Pedro da Silva Paiva [paiva.code@gmail.com](mailto:paiva.code@gmail.com)\n" +
                        "- Vitório Honorato de Freitas Pereira [vitoriohonorato166@gmail.com](mailto:vitoriohonorato166@gmail.com)\n\n" +
                        "**Prof.:** José Erico Gomes da Silva  \n" +
                        "**Data:** Junho/2025\n\n" +
                        "[Repositório no GitHub](https://github.com/ryanpaiva-coder/saude-plus-api)\n" +
                        "---"
                    )
            )
            .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
            .components(new Components().addSecuritySchemes(securitySchemeName,
                new SecurityScheme()
                    .name(securitySchemeName)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
            ));
    }
}
