/**
 * 
 */
package com.berina.expensereports.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.berina.expensereports.model.Receipt;

/**
 * @author berina
 *
 */
@Repository
public class ReceiptDaoImpl implements ReceiptDao {
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	private static int maxID = -1;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	/* (non-Javadoc)
	 * @see com.berina.expensereports.dao.ReceiptDao#getAllReceiptsByUser(java.lang.String)
	 */
	@Override
	public List<Receipt> getAllReceiptsByUser(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		String sql = "SELECT * FROM receipt WHERE username=:username";
		List<Receipt> result = namedParameterJdbcTemplate.query(sql, params, new ReceiptMapper());
		return result;
	}
	
	@Override
	public List<Receipt> getReceiptsByUserFilter(String username, Map<String, Object> params) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM receipt WHERE ");
		for (String key : params.keySet()) {
			sql.append(key + "=:" + key + " AND ");
		}
		sql.delete(sql.lastIndexOf(" AND "), sql.length());
		List<Receipt> result = namedParameterJdbcTemplate.query(sql.toString(), params, new ReceiptMapper());
		return result;
	}
	
	@Override
	@Modifying(clearAutomatically=true)
	public void save(Receipt receipt) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO receipt (username, file, category, payment) "
				+ "VALUES (:username,:file,:category,:payment)");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("username", receipt.getUsername());
		paramMap.put("file", receipt.getFile());
		paramMap.put("category", receipt.getCategory());
		paramMap.put("payment", receipt.getPayment());
		namedParameterJdbcTemplate.update(sql.toString(), paramMap);
	}
	
	private static final class ReceiptMapper implements RowMapper<Receipt> {

		public Receipt mapRow(ResultSet rs, int rowNum) throws SQLException {
			Receipt receipt = new Receipt();
			receipt.setUsername(rs.getString("username"));
			receipt.setCategory(rs.getString("category"));
			receipt.setFile(rs.getString("file"));
			receipt.setPayment(rs.getString("payment"));
			return receipt;
		}
	}
}