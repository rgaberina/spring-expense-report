/**
 * 
 */
package com.berina.expensereports.config.db;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.berina.expensereports.dao.UserDao;
import com.berina.expensereports.model.User;

/**
 * @author berina
 *
 */
@Configuration
@ComponentScan
public class SpringRootConfig {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private UserDao userDao;

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
	
	@PostConstruct
	public void addUsers() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user1 = new User();
		user1.setUsername("user1");
		user1.setPassword(encoder.encode("Pass1"));
		userDao.save(user1);
		
		User user2 = new User();
		user2.setUsername("user2");
		user2.setPassword(encoder.encode("Pass2"));
		userDao.save(user2);
		
		User user3 = new User();
		user3.setUsername("user3");
		user3.setPassword(encoder.encode("Pass3"));
		userDao.save(user3);
	}
}
