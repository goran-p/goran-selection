package com.infinum.selection.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.infinum.selection.exception.UnexpectedNumberOfDBOperationsException;
import com.infinum.selection.model.User;

/**
 * Service class containing methods with logic for:
 * <li>registering new user into the system</li>
 * <li>logging user into the system</li>
 * <li>encrypting users password</li>
 */
@Service
public class UserService implements IUserService {
	
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public void register(final String email, final String password) {
		
		final String insertSql = "INSERT INTO user(email, password) VALUES(?, ?)";
		final int inserted = jdbc.update(insertSql, email, password);
		if(inserted!=1) {
			final String message = "Expected to insert 1 record into the USER table, but instead inserted "+inserted+" records";
			throw new UnexpectedNumberOfDBOperationsException(message);
		}
	}

	@Override
	public Long login(final String email, final String password) {
		
		final String selectSql = "SELECT * FROM user WHERE email = ? AND password = ?";
		final User login = jdbc.queryForObject(selectSql, new Object[] {email, password}, new BeanPropertyRowMapper<>(User.class));
		// TODO: validate login object
		return login.getToken();
	}

	@Override
	public String hashPassword(final String password) {
		Objects.requireNonNull(password);
		String encrypted = "";
		try {
			final MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			encrypted = new String(md.digest());
		} catch(final NoSuchAlgorithmException nsae) {
			// TODO: handle exception
		}
		
		return encrypted;
	}

}
