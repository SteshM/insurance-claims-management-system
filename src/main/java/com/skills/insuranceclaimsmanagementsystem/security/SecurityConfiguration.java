package com.skills.insuranceclaimsmanagementsystem.security;
import com.skills.insuranceclaimsmanagementsystem.utils.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@Configuration
@Setter
@Getter
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsService userDetailsService;


    public void userDetailsService(AuthenticationManagerBuilder authenticationManagerBuilder)throws Exception
    {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(this.passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/allow/**"
                        ).permitAll()
                        .requestMatchers("/api/customers/**").hasAnyAuthority("CUSTOMER")
                        .requestMatchers("/api/insurer/**").hasAnyAuthority("INSURER")
                        .anyRequest().authenticated()
                )

                .sessionManagement((sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(new JwtAuthFilter(jwtUtil,userDetailsService ), UsernamePasswordAuthenticationFilter.class)
                .cors((cors) -> cors
                        .configurationSource(request-> {
                            CorsConfiguration configuration = new CorsConfiguration();
                            configuration.setAllowedOrigins(List.of("*"));
                            configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
                            configuration.setAllowedHeaders(List.of("*"));
                            return configuration;
                        })
                )
                .build();
    }




    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}