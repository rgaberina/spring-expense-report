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
	
	@Column
	private int enabled;

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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
}
