package ru.isys.groupwagering.config;

import ru.isys.groupwagering.component.SampleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private SampleProvider sapAuthProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(sapAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/js/*").permitAll()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/ui/userRegistrationForm").permitAll()
               // .antMatchers("/ui/*").hasAnyRole("USER")
                .antMatchers("/wagering/**").authenticated()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/protected/*").authenticated()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/login*").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .defaultSuccessUrl("/ui/userRegistrationForm.html")
                .failureUrl("/login.html?error=true")
                .and()
                .logout().permitAll().logoutSuccessUrl("/login.html")
        .and().csrf().disable();
    }
}


