package com.makersacademy.toon_together;

import com.makersacademy.toon_together.handler.CustomLoginSuccessHandler;
import com.makersacademy.toon_together.handler.CustomLogoutSuccessHandler;
import com.makersacademy.toon_together.handler.CustomSignUpSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    CustomSignUpSuccessHandler customSignUpSuccessHandler;

    @Autowired
    CustomLoginSuccessHandler customLoginSuccessHandler;

    @Autowired
    CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasRole("USER")
                .antMatchers("/playlists").hasRole("USER")
                .antMatchers("/myprofile").hasRole("USER")
                .antMatchers("/myprofile/updateProfilePicture").hasRole("USER")
                .antMatchers("/users", "/login", "/logout", "/signup").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(customLoginSuccessHandler)  // Custom login success handler
                .and() // Ensure proper method chaining
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .permitAll()
                .and()
                .formLogin() // This is for handling signup
                .loginPage("/login")
                .successHandler(customSignUpSuccessHandler)  // Custom success handler for signup
                .and()
                .csrf().ignoringAntMatchers("/logout"); // CSRF configuration
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
