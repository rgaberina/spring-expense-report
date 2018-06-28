/**
 * 
 */
package com.berina.expensereports.model;

import javax.persistence.Column;

/**
 * @author berina
 *
 */
public class User {

	@Column
	private String username;
	
	@Column
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
