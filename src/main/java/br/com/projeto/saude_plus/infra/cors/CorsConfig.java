package br.com.projeto.saude_plus.infra.cors;

import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry; // Não é mais necessário para CORS aqui
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // @Override // Remova ou comente este método completamente
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //             .allowedOrigins("http://localhost:5173")
    //             .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
    //             .allowedHeaders("*")
    //             .allowCredentials(true);
    // }

    // Se você tiver outras configurações WebMvcConfigurer aqui, elas podem permanecer.
}