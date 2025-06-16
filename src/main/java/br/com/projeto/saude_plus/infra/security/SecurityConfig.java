package br.com.projeto.saude_plus.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // Importar HttpMethod
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer; // Importar Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration; // Importar CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource; // Importar CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; // Importar UrlBasedCorsConfigurationSource

import java.util.List; // Importar List

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity // @EnableWebSecurity geralmente é usado aqui também, mas @EnableMethodSecurity é para segurança em nível de método. Se você não tiver @EnableWebSecurity, adicione-o.
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults()) // Habilita CORS usando o bean corsConfigurationSource() abaixo
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Permite TODAS as requisições OPTIONS (preflight)
                .requestMatchers(
                    "/api/auth/**",
                    "/api/pacientes", // Se este for para cadastro público, está OK.
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html"
                ).permitAll()
                .requestMatchers("/api/pacientes/me").hasRole("PACIENTE")
                .anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); // Sua origem do frontend
        configuration.setAllowedMethods(List.of("*")); // Permite TODOS os métodos HTTP
        configuration.setAllowedHeaders(List.of("*")); // Permite TODOS os cabeçalhos
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L); // Cache da preflight request por 1 hora

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica a configuração a todos os paths
        return source;
    }
}