package com.example.bhp.data_initializer;
import com.example.bhp.auth.CustomAccessDeniedHandler;
import com.example.bhp.auth.CustomAuthenticationEntryPoint;
import com.example.bhp.auth.CustomLogoutSuccessHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@NoArgsConstructor
@AllArgsConstructor
public class SecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    CustomLogoutSuccessHandler successHandler;

    @Autowired
    CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()
                .authorizeHttpRequests()
                        .requestMatchers( "/api/logout").permitAll()
                        .requestMatchers("/api/save").hasRole("ADMIN")
                        .anyRequest().authenticated()
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint).and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler(successHandler)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        //SecurityContextHolder.clearContext();
        AuthenticationManager man = config.getAuthenticationManager();
        return man;
    }

}

