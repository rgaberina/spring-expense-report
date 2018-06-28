/**
 * 
 */
package com.berina.expensereports.dao;

import java.util.List;
import java.util.Map;

import com.berina.expensereports.model.Receipt;

/**
 * @author berina
 *
 */
public interface ReceiptDao {
	
	public List<Receipt>  getAllReceiptsByUser(String username);
	
	public List<Receipt> getReceiptsByUserFilter(String username, Map<String, Object> params);
	
	public void save(Receipt receipt);

}
