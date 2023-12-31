package com.project.passmanager.main.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Класс конфигурирования Spring Security
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
    /**
     * В данном бине реализована доступность путей разным ролям
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/registration", "/login", "/logout", "/main").permitAll()
                                .requestMatchers("/delete/**").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(Customizer.withDefaults());

        http.csrf().disable();

        return http.build();
    }

    /**
     * PasswordEncoder используется для выполнения одностороннего преобразования пароля с целью безопасного хранения
     *
     * @return NoOpPasswordEncoder возвращает пароли в виде обычного текста
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(15);
    }

}
