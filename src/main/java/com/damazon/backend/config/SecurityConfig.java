package com.damazon.backend.config;

import com.damazon.backend.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // This annotation is scanned by spring for CONFIGURATION
@EnableWebSecurity // Using Spring Security
public class SecurityConfig {

    // Needed to give to DaoProvider, but should implement UserDetailsService provided by SPRING SECURITY
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    // Internally used by SPRING SECURITY, but now we provide it
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

    // Internally used by SPRING SECURITY, but now we provide it
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                /* Disabling csrf for all incoming requests */
                .csrf(customizer ->
                        customizer.disable()
                )

                /* making authentication mandatory for all incoming requests */
                .authorizeHttpRequests(request ->
                        request.anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
        ;

        return http.build();
    }
}
