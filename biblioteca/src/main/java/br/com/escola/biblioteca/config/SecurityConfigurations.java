package br.com.escola.biblioteca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                // Desligar a proteção
                .csrf(csrf -> csrf.disable())
                
                // Guardar o estado do user
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                
                // ACL de cria
                .authorizeHttpRequests(req -> {
                    
                    // Rota Login pública
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll();
                    
                    // Rota Swagger pública
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                    
                    // Exigir autenticação
                    req.anyRequest().authenticated();
                })
                
                // Adc mais segurança antes do filtro padrão de autenticação do Java
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    //Exportar o authentication manager

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    
        return configuration.getAuthenticationManager();
    }

    //Configura hashing

    @Bean
    public PasswordEncoder passwordEncoder() {
    
        return new BCryptPasswordEncoder();
    }

}






