package com.pakskiy.news.config;

import com.pakskiy.news.security.jwt.JwtConfigurer;
import com.pakskiy.news.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig{
    private final JwtTokenProvider jwtTokenProvider;
    private static final String ADMIN_ENDPOINT = "/api/users/**";
    private static final String USER_ENDPOINT = "/api/v1/auth/login";
    private static final String MODERATOR_ENDPOINT = "/api/v1/auth/login";
    private static final String PUBLIC_ENDPOINT = "/api/v1/auth/login";

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .authorizeHttpRequests((authz) -> {
                            try {
                                authz
//                                        .requestMatchers(antMatcher("/api/news/save/**")).hasAnyRole("ADMIN", "MODERATOR")
//                                        .requestMatchers(antMatcher("/api/news/items/**")).hasAnyRole("ADMIN", "MODERATOR")
//                                        .requestMatchers(antMatcher(ADMIN_ENDPOINT)).hasRole("ADMIN")
//                                        //.requestMatchers(antMatcher("/**")).permitAll()
                                        .anyRequest().permitAll()
                                        .and()
                                        .apply(new JwtConfigurer(jwtTokenProvider));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return http.build();
    }
}