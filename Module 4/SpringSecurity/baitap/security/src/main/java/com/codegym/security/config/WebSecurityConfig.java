package com.codegym.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}12345").roles("USER")
                .and()
                .withUser("admin").password("{noop}12345").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/", "/blog").hasAnyRole("USER","ADMIN")
                .and()
                .authorizeRequests().antMatchers("/edit-blog/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/create-blog").hasRole("USER")
                .and()
                .formLogin()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/blog");
        http.exceptionHandling().accessDeniedPage("/403");
    }
}
