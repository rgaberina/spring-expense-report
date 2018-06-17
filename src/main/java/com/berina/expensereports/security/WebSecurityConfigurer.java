/**
 * 
 */
package com.berina.expensereports.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author berina
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
 
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("user1").password("{noop}Pass")
          .authorities("USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          //.antMatchers("/upload").authenticated()
          //.antMatchers("/view").authenticated()
//          .antMatchers("/home").authenticated()
          .antMatchers("/login").permitAll()
          .anyRequest().authenticated()
          .and()
          .httpBasic()
          .authenticationEntryPoint(authenticationEntryPoint);
         // .and().formLogin().loginPage("/login").permitAll()
          //.defaultSuccessUrl("/").loginProcessingUrl("/j_spring_security_check").and()
          //.authorizeRequests().and().logout().logoutUrl("/logout").logoutSuccessUrl("/logout.html").permitAll();
      http.csrf().disable();
  
 
        http.addFilterAfter(new CustomFilter(),
          BasicAuthenticationFilter.class);
        
      /* http.formLogin()
        .loginPage("/login.html")
        .defaultSuccessUrl("/uploadForm.html");*/
    }
}
