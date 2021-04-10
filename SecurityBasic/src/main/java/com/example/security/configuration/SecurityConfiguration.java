package com.example.security.configuration;


import com.example.security.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Qualifier("myUserDetailService")
    @Autowired
    UserDetailsService userDetailsService;

    @Override   //authentication configure
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* Not work with JPA
        auth.jdbcAuthentication()
            .dataSource(dataSource)
//            .withDefaultSchema()        //CREATE schema cho default datasource
//            .withUser(
//                    User.withUsername("user")
//                    .password("pass")
//                    .roles("USER")
//            )
//            .withUser(
//                    User.withUsername("admin")
//                            .password("pass")
//                            .roles("ADMIN")
//            );
            .usersByUsernameQuery("select username,password,enabled "+  //spring security know which schema to query
                    "from users "+
                    "where username = ?")
            .authoritiesByUsernameQuery("select username,authority "+
                    "from authorities "+
                    "where username = ?");

         */

        auth.userDetailsService(userDetailsService);

    }

    @Override //Authorization
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN","USER")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
