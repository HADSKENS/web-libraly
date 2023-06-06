package ru.skypro.lessons.springboot.weblibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
        UserDetails ivan = User.withUsername("Ivan")
                .password(passwordEncoder.encode("ivan1234"))
                .roles("USER")
                .build();
        UserDetails vladimir = User.withUsername("Vladimir")
                .password(passwordEncoder.encode("vladimir1234"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin1234"))
                .roles("USER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(ivan, vladimir, admin);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .authorizeHttpRequests(this::customizeRequest);
        return http.build();
    }
    private void customizeRequest(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        try {
            registry.requestMatchers(new AntPathRequestMatcher("/admin/**"))
                    .hasAnyRole("ADMIN")

                    .requestMatchers(new AntPathRequestMatcher("/**"))
                    .hasAnyRole("USER")

                    .and()
                    .formLogin().permitAll()

                    .and()
                    .logout().logoutUrl("/logout");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}