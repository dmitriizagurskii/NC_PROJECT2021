package com.netcracker.edu.interview.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_LOGIN = "1";
    private static final String ADMIN_PASSWORD = "1";

    private static final String USER_LOGIN = "2";
    private static final String USER_PASSWORD = "2";

    enum Role {
        USER, ADMIN;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(
                        userBuilder.username(ADMIN_LOGIN)
                                .password(ADMIN_PASSWORD)
                                .roles(Role.USER.name(), Role.ADMIN.name())
                )
                .withUser(
                        userBuilder.username(USER_LOGIN)
                                .password(USER_PASSWORD)
                                .roles(Role.USER.name())
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();  //непонятная фигня из-за которой все работает
        http.authorizeRequests()
                .antMatchers("/custom").hasAnyRole(Role.USER.name())
                .antMatchers("/custom/add").hasRole(Role.ADMIN.name())
                .and()
                .formLogin()
                .and()
                .httpBasic();

    }

}
