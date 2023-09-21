package com.osypenko.config;

import com.osypenko.model.users.Role;
import org.springframework.security.core.userdetails.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.osypenko.constant.NameMapping.*;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .formLogin().and()
                .authorizeHttpRequests()
                    .requestMatchers(LOGIN, REGISTRATION, CODE_FOR_REGISTRATION, PASSWORD_RECOVERY, CODE_PASSWORD_RECOVERY, NEW_PASSWORD, GET_REGISTRATION_CODE, NEW_USER).permitAll()
                    .requestMatchers(DIRECTORY_CSS, DIRECTORY_JAVASCRIPT, DIRECTORY_IMAGES).permitAll()
                    .requestMatchers(USER_PAGE, QUESTION, TESTING, ALL_STATISTICS, STATISTIC).hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                    .requestMatchers(ADMIN_PAGE, CREATE_AND_UPDATE_QUESTION, CREATE_AND_UPDATE_TESTING).hasRole(Role.ADMIN.name())
                    .anyRequest().authenticated().and()
                .exceptionHandling()
                    .authenticationEntryPoint((request, response, authException) -> response.sendRedirect("/login")).and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate) {
        return username -> jdbcTemplate.query("select * from users where email = ?",
                (rs, i) -> User.builder()
                        .username(rs.getString("email"))
                        .password(rs.getString("password"))
                        .authorities(rs.getString("role"))
                        .build(), username).stream().findFirst().orElse(null);
    }
}
