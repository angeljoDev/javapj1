package com.udacity.jwdnd.course1.cloudstorage.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        // Use the custom authentication provider
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // CSRF protection is disabled (not recommended for production)
                .authorizeRequests()
                .antMatchers("/login", "/signup", "/css/**", "/js/**").permitAll()  // Allow public access to login and signup
                .anyRequest().authenticated()  // Require authentication for all other requests
                .and()
                .formLogin()
                .loginPage("/login")  // Custom login page
                .defaultSuccessUrl("/home", true)  // Redirect to home page on successful login
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))  // Logout through a POST request
                .logoutSuccessUrl("/login?logout")  // Redirect to login page after logout
                .invalidateHttpSession(true)  // Invalidate session
                .clearAuthentication(true)  // Clear authentication
                .permitAll()
                .and()
                .sessionManagement()
                .invalidSessionUrl("/login")  // Redirect to login page for invalid sessions
                .maximumSessions(1)  // Allow only one session per user
                .expiredUrl("/login?expired")  // Redirect to login page when session expires
                .and()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");  // Custom page for access denied errors
    }
}
