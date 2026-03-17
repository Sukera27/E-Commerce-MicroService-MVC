package com.sukera.apigetway.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeExchange(auth -> auth
                .pathMatchers("/actuator/**").permitAll() // ejemplo de ruta pública
                .anyExchange().authenticated()
            )
            .oauth2Login(withDefaults())
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults())); // <--- AÑADE ESTO
            
        return http.build();
    }
}
