package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();
        httpSecurity.cors().disable();
        httpSecurity.csrf().disable();

        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/orders").hasAnyRole("CUSTOMER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/orders").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/orders/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/orders/**").hasAnyRole("ADMIN", "STORE_MANAGER")
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/**")
                .permitAll();

        return httpSecurity.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN")
                .and()
                .withUser("customer").password("{noop}customer").roles("CUSTOMER")
                .and()
                .withUser("store_manager").password("store_manager").roles("STORE_MANAGER");
    }
}