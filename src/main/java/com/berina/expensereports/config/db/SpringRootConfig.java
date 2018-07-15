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

import com.berina.expensereports.dao.ReceiptDao;
import com.berina.expensereports.dao.UserDao;
import com.berina.expensereports.model.Receipt;
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
	
	@Autowired
	private ReceiptDao receiptDao;

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
	
	@PostConstruct
	public void addData() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		User user1 = new User();
		user1.setUsername("user1");
		user1.setPassword(encoder.encode("Pass1"));
		user1.setEnabled(1);
		userDao.save(user1);
		
		User user2 = new User();
		user2.setUsername("user2");
		user2.setPassword(encoder.encode("Pass2"));
		user2.setEnabled(1);
		userDao.save(user2);
		
		User user3 = new User();
		user3.setUsername("user3");
		user3.setPassword(encoder.encode("Pass3"));
		user3.setEnabled(1);
		userDao.save(user3);
		
		Receipt receipt1 = new Receipt();
		receipt1.setCategory("Grocery");
		receipt1.setPayment("Credit");
		receipt1.setUsername("user1");
		receipt1.setFile("Receipts/Electric Residential-1.jpg");
		receiptDao.save(receipt1);
		
		Receipt receipt2 = new Receipt();
		receipt2.setCategory("Grocery");
		receipt2.setPayment("Cash");
		receipt2.setUsername("user1");
		receipt2.setFile("Receipts/Electric Residential-1.jpg");
		receiptDao.save(receipt2);
		
		Receipt receipt3 = new Receipt();
		receipt3.setCategory("Auto");
		receipt3.setPayment("Credit");
		receipt3.setUsername("user1");
		receipt3.setFile("Receipts/Electric Residential-1.jpg");
		receiptDao.save(receipt3);
		
		Receipt receipt4 = new Receipt();
		receipt4.setCategory("Clothing");
		receipt4.setPayment("Debit");
		receipt4.setUsername("user1");
		receipt4.setFile("Receipts/Electric Residential-1.jpg");
		receiptDao.save(receipt4);
		
		Receipt receipt5 = new Receipt();
		receipt5.setCategory("Clothing");
		receipt5.setPayment("Cash");
		receipt5.setUsername("user1");
		receipt5.setFile("Receipts/Electric Residential-1.jpg");
		receiptDao.save(receipt5);
		
		Receipt receipt6 = new Receipt();
		receipt6.setCategory("Grocery");
		receipt6.setPayment("Debit");
		receipt6.setUsername("user1");
		receipt6.setFile("Receipts/Electric Residential-1.jpg");
		receiptDao.save(receipt6);
		
		Receipt receipt7 = new Receipt();
		receipt7.setCategory("Shopping");
		receipt7.setPayment("Cash");
		receipt7.setUsername("user1");
		receipt7.setFile("Receipts/Electric Residential-1.jpg");
		receiptDao.save(receipt7);
		
		Receipt receipt8 = new Receipt();
		receipt8.setCategory("Clothing");
		receipt8.setPayment("Credit");
		receipt8.setUsername("user1");
		receipt8.setFile("Receipts/Electric Residential-1.jpg");
		receiptDao.save(receipt8);
		
		
	}
}