package com.vkannuri.security.app.configuration;

import com.vkannuri.security.app.auth.GuestUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/** @author Venu Kannuri . */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Resource private GuestUserDetailService userDetailService;

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailService);
    /**
     * for BCryptPasswordEncoder to convert simple text to BCryptPasswordEncoder use url
     * https://bcrypt-generator.com/
     */
    daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(11));
    return daoAuthenticationProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/", "/index", "/css/*", "/js/*")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
  }

  // Never Recommended for Production
  /*@Bean
  @Override
  public UserDetailsService userDetailsService() {
    List<UserDetails> userDetails = new ArrayList<>();
    userDetails.add(
        User.withDefaultPasswordEncoder()
            .username("Venu")
            .password("password")
            .roles("USER", "ADMIN")
            .build());
    userDetails.add(
        User.withDefaultPasswordEncoder()
            .username("Kannuri")
            .password("password")
            .roles("USER")
            .build());
    return new InMemoryUserDetailsManager(userDetails);
  }

   */
}
