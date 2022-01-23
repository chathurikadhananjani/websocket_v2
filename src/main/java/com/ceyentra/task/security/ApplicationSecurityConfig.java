package com.ceyentra.task.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.ceyentra.task.security.ApplicationUserRole.admin;
import static com.ceyentra.task.security.ApplicationUserRole.buyer;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //  .and()

                .csrf().disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()


                .authorizeRequests()
                //.antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/items/**").hasAuthority(ApplicationUserPermission.ITEM_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/api/items/**").hasAuthority(ApplicationUserPermission.ITEM_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/items/**").hasAuthority(ApplicationUserPermission.ITEM_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/api/items**").hasAnyRole(admin.name(), buyer.name())


                .anyRequest()
                .authenticated();


        // .and()
        // .httpBasic()
        //.and()
        //.rememberMe()
        ;
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails buyerUser = User.builder()
                .username("john")
                .password(passwordEncoder.encode("test123"))
                .authorities(buyer.getGrantedAuthorities())
                // .roles(ApplicationUserRole.buyer.name())
                .build();

        UserDetails adminUser = User.builder()
                .username("mary")
                .password(passwordEncoder.encode("test123"))
                .authorities(admin.getGrantedAuthorities())
                //.roles(ApplicationUserRole.admin.name())
                .build();
        return new InMemoryUserDetailsManager(
                buyerUser,
                adminUser
        );
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder);
//        provider.setUserDetailsService(applicationUserService);
//        return provider;
//    }


}
