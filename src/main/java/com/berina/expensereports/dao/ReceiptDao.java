/**
 * 
 */
package com.berina.expensereports.dao;

import java.util.List;
import java.util.Map;

import com.berina.expensereports.model.ReceiptModel;

/**
 * @author berina
 *
 */
public interface ReceiptDao {
	
	public List<ReceiptModel>  getAllReceiptsByUser(String username);
	
	public List<ReceiptModel> getReceiptsByUserFilter(String username, Map<String, Object> params);
	
	

}
