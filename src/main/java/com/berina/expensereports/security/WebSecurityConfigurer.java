/**
 * 
 */
package com.berina.expensereports.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private DataSource datasource;

	/*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("user1").password("{noop}Pass")
          .authorities("USER");
    }*/

	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {      
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username, password, 1 from user where username = ?")
			.authoritiesByUsernameQuery("SELECT u.username, role.name FROM user u JOIN user_roles "
					+ "ON u.username = user_roles.username JOIN Role on user_roles.roles_Id = role.id "
					+ "WHERE u.username = ? ").passwordEncoder(new BCryptPasswordEncoder());

	}*/
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
	    auth.jdbcAuthentication().dataSource(datasource);
	}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
	  auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery(
			"select username,password, enabled from user where username=?")
		.authoritiesByUsernameQuery(
			"select username, roleID from user_roles where username=?");
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
