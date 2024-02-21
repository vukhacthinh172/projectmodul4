package ra.demoprojectmodul4.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.GET, "/css/**", "/js/**","/fonts/**","/favicon.ico", "/about").permitAll()
                        // Ko cần check authencation
                        .requestMatchers("/register", "/index").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        // tất cả các request còn lại đều phải xaác thực
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/admin/index", true)
                        .successHandler((request, response, authentication) -> {
                            for (GrantedAuthority authority : authentication.getAuthorities()){
                                if (authority.getAuthority().equals("ADMIN")){
                                    response.sendRedirect("/admin/product");
                                } else if (authority.getAuthority().equals("CUSTOMER")) {
                                    response.sendRedirect("/index");
                                }
                            }
                        })
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true));

        return http.build();
    }


}
