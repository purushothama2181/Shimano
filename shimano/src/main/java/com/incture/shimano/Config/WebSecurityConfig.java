package com.incture.shimano.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().
                and().
                authorizeRequests().
                antMatchers(HttpMethod.GET, "/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/*").hasRole("ADMIN")
                .and().csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}cpi@1234").roles("ADMIN");
    }
}

