package com.osypenko.config;

import com.osypenko.model.users.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.osypenko.constant.Endpoints.*;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PASSWORD_ENCODER;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(REGISTRATION, GET_REGISTRATION_CODE, CODE_FOR_REGISTRATION, NEW_USER).permitAll()
                .requestMatchers(PASSWORD_RECOVERY, CODE_PASSWORD_RECOVERY, NEW_PASSWORD, CONFIRMATION_CODE, SAVE_NEW_PASSWORD).permitAll()
                .requestMatchers(DIRECTORY_CSS, DIRECTORY_JAVASCRIPT, DIRECTORY_IMAGES).permitAll()
                .requestMatchers(USER_PAGE, QUESTION, TESTING, ALL_STATISTICS, STATISTIC, SWAGGER, FEEDBACK).hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .requestMatchers(ADMIN_PAGE, ADMIN_SEARCH_QUESTION, CREATE_AND_UPDATE_QUESTION, ADMIN_SEARCH_TESTING, CREATE_AND_UPDATE_TESTING, REDIRECT_ADMIN_PAGE).hasRole(Role.ADMIN.name())
                .requestMatchers(USER_API).hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .requestMatchers(API + INFO, API + DELETE_USER).hasRole(Role.ADMIN.name())
                .anyRequest().authenticated()
                .and().formLogin().permitAll().loginPage(LOGIN).defaultSuccessUrl(USER_PAGE, true)
                .and().httpBasic()
                .and().csrf().disable();
        return http.build();
    }
}
