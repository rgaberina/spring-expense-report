/**
 * 
 */
package com.berina.expensereports.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.berina.expensereports.model.ReceiptModel;

/**
 * @author berina
 *
 */
@Repository
public class ReceiptDaoImpl implements ReceiptDao {
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	/* (non-Javadoc)
	 * @see com.berina.expensereports.dao.ReceiptDao#getAllReceiptsByUser(java.lang.String)
	 */
	@Override
	public List<ReceiptModel> getAllReceiptsByUser(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		String sql = "SELECT * FROM receipt WHERE username=:username";
		List<ReceiptModel> result = namedParameterJdbcTemplate.query(sql, params, new ReceiptMapper());
		return result;
	}
	
	@Override
	public List<ReceiptModel> getReceiptsByUserFilter(String username, Map<String, Object> params) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM receipt WHERE ");
		for (String key : params.keySet()) {
			sql.append(key + "=:" + key + " AND ");
		}
		sql.delete(sql.lastIndexOf(" AND "), sql.length());
		List<ReceiptModel> result = namedParameterJdbcTemplate.query(sql.toString(), params, new ReceiptMapper());
		return result;
	}

	private static final class ReceiptMapper implements RowMapper<ReceiptModel> {

		public ReceiptModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReceiptModel receipt = new ReceiptModel();
			receipt.setUsername(rs.getString("username"));
			receipt.setCategory(rs.getString("category"));
			receipt.setFile(rs.getString("file"));
			receipt.setPayment(rs.getString("payment"));
			return receipt;
		}
	}
}
