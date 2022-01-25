package com.spring.docker.configurations;

import com.spring.docker.filter.JwtFilter;
import com.spring.docker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService);

    }

    // This method is used to allow authenticate method to be away from security check as the authenticate method or controller is utilized for generating the tokens.

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().
                disable().
                    authorizeRequests().

                //Updating that authenticate url needs not to be authenticated as this would help in token generation and authentication.

                        antMatchers("/authenticate").
                            permitAll().
                                anyRequest().
                                    authenticated().

                //Updating that we do not need a session management policy as we are moving with stateless communication.

                                        and().
                                            sessionManagement().
                                                sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                //Adding the JWT filter which we created prior to UserNamePasswordAuthenticationFilter

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    //Updating that we are not using any password encoder.

    @Bean
    public PasswordEncoder passwordEncoder(){

        return NoOpPasswordEncoder.getInstance();

    }

    //Creating the bean for AuthenticationManager which 

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {

        return super.authenticationManager();

    }


}
