package tr.com.obss.jip2022.bookportalproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tr.com.obss.jip2022.bookportalproject.dto.MyUserDetails;
import tr.com.obss.jip2022.bookportalproject.model.User;
import tr.com.obss.jip2022.bookportalproject.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, DaoAuthenticationProvider daoAuthenticationProvider) throws Exception {
        return http
                .cors()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //.antMatchers("/basic/auth")
                //.antMatchers("/**")
                //.permitAll().and().build();
                //.and().build();
                //.antMatchers(HttpMethod.POST, "/api/user"
                .antMatchers("/api/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/api/user/**")
                .hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .authenticationProvider(daoAuthenticationProvider)
                .build();

    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }


    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            final User user = userService.findByUsername(username);
            final List<String> roles = user.getRoles().stream().map(role -> role.getName().name()).toList();
            return new MyUserDetails(user.getUsername(), user.getPassword(), roles);
        };
    }
}
