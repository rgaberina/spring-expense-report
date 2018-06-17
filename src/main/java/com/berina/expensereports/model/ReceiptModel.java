/**
 * 
 */
package com.berina.expensereports.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author berina
 *
 */
@Entity
public class ReceiptModel {
	
	@Id
	@GeneratedValue
	private Long iD;
	
	@Column
	private MultipartFile file;
	
	@Column
	private String category;
	
	@Column
	private String payment;
	
	public MultipartFile getFile() {
		return file;
	}
	
	public void setFile(MultipartFile file) {
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

}
