package com.osypenko.config;

import com.osypenko.model.users.Role;
import org.springframework.security.config.Customizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.osypenko.constant.NameMapping.*;

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
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests()
                .requestMatchers(REGISTRATION, GET_REGISTRATION_CODE, CODE_FOR_REGISTRATION, NEW_USER).permitAll()
                .requestMatchers(PASSWORD_RECOVERY, CODE_PASSWORD_RECOVERY, NEW_PASSWORD, CONFIRMATION_CODE, SAVE_NEW_PASSWORD).permitAll()
                .requestMatchers(DIRECTORY_CSS, DIRECTORY_JAVASCRIPT, DIRECTORY_IMAGES).permitAll()
                .requestMatchers(USER_PAGE, QUESTION, TESTING, ALL_STATISTICS, STATISTIC).hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                .requestMatchers(ADMIN_PAGE, CREATE_AND_UPDATE_QUESTION, CREATE_AND_UPDATE_TESTING).hasRole(Role.ADMIN.name())
                .anyRequest().authenticated().and()
                .formLogin().permitAll().loginPage(LOGIN).defaultSuccessUrl(USER_PAGE).and()
                .csrf().disable();
        return http.build();
    }
}
