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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.berina.expensereports.model.User;

/**
 * @author berina
 *
 */
@Repository
public class UserDaoImpl implements UserDao {
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public User findByUsername(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		String sql = "SELECT * FROM user WHERE username=:username";
		List<User> result = namedParameterJdbcTemplate.query(sql, params, new UserMapper());
		for (User r: result) {
			System.out.println(r);
		}
		return result.get(0);
	}

	@Override
	@Modifying
	public void save(User user) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO user (username, password) "
				+ "VALUES (:username,:password)");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("username", user.getUsername());
		paramMap.put("password", user.getPassword());
		System.out.println(user.getPassword());
		namedParameterJdbcTemplate.update(sql.toString(), paramMap);
	}
	
	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	}
}
