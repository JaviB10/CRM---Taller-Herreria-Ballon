package com.javierballon.springboot.crm.springboot_crm.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.javierballon.springboot.crm.springboot_crm.security.filter.JwtAuthenticationFilter;
import com.javierballon.springboot.crm.springboot_crm.security.filter.JwtValidationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) //Este es para usar con anotaciones en los controllers
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.GET, "/api/clients", "/api/clients/{id}", "/api/budgets/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/users/register", "/api/users/", "/api/jobs/{id}", "/api/budgets/{id}", "/api/materials/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/clients/{id}", "/api/jobs/{id}", "/api/materials/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/clients/{id}", "/api/jobs/{id}", "/api/materials/{id}").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtValidationFilter(authenticationManager()))
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(management -> management
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
	}

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
            new CorsFilter(corsConfigurationSource())
        );
        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return corsBean;
    }
}
