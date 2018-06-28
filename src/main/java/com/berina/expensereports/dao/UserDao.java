/**
 * 
 */
package com.berina.expensereports.dao;

import com.berina.expensereports.model.User;

/**
 * @author berina
 *
 */
public interface UserDao {
	
	public User findByUsername(String username) ;
	public void save(User user);

}
