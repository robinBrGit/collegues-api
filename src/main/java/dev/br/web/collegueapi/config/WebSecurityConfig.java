package dev.br.web.collegueapi.config;

import dev.br.web.collegueapi.filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;


    // TODO
    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // désactivation de la protection CSRF
                // non utilisée dans le cadre d'une Web API
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()

                // un POST /auth n'est pas soumise à authentification
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                // accès à la console h2 sans authentification
                .antMatchers("/h2-console/**").permitAll()

                // Les autres requêtes sont soumises à authentification
                .anyRequest().authenticated()

                // accès à la console h2 sans authentification
                .and().headers().frameOptions().disable()
                .and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                // Gestion de la déconnexion
                // /POST /logout
                .logout()

                // en cas de succès un OK est envoyé (à la place d'une redirection vers /login)
                .logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpStatus.OK.value()))

                // suppression du cookie d'authentification
                .deleteCookies(TOKEN_COOKIE); // Gestion de la déconnexion

    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // you USUALLY want this
        // likely you should limit this to specific origins
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PATCH");
        config.addAllowedMethod("PUT");
        source.registerCorsConfiguration("/logout", config);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
