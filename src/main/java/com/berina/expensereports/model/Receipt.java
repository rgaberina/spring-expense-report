/**
 * 
 */
package com.berina.expensereports.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author berina
 *
 */
@Entity
public class Receipt {
	
	@Id
	@Column
	private Long iD;
	
	@Column
	private String username;
	
	@Column
	private String file;
	
	@Column
	private String category;
	
	@Column
	private String payment;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFile() {
		return file;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getPayment() {
		return payment;
	}
	
	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.getUsername() + " ");
		str.append(this.getFile() + " ");
		str.append(this.getCategory() + " ");
		str.append(this.getPayment());
		return str.toString();
	}

}
