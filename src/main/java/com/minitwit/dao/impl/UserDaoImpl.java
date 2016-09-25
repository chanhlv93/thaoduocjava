package com.minitwit.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.minitwit.config.DatabaseConfig;
import com.minitwit.dao.UserDao;
import com.minitwit.model.User;
import com.minitwit.util.PasswordUtil;

@Repository
public class UserDaoImpl implements UserDao {
	
	Connection c = DatabaseConfig.GetDBSource();
	
	@Override
	public User getUserbyUsername(String username) {
		List<User> list = new ArrayList<User>();
		try {
			PreparedStatement updateemp = c.prepareStatement("SELECT * FROM tbuser WHERE username=?");
			updateemp.setString(1, username);
			
			ResultSet rs = updateemp.executeQuery();

			if (!rs.isBeforeFirst()) {
				System.out.println("No data");
			} else {
				while (rs.next()) {
					User user = new User(rs.getString("username"), rs.getString("password"));
					list.add(user);
				}
				rs.close();
			}
			updateemp.close();
			//c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + "::: " + e.getMessage());
			//System.exit(0);
		}

		User result = null;
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}

		return result;
	}

	@Override
	public void registerUser(User user) {
        try {
			PreparedStatement updateemp = c.prepareStatement("INSERT INTO tbuser VALUES (?,?)");
			updateemp.setString(1, user.getUsername());
			updateemp.setString(2, PasswordUtil.hashPassword(user.getPassword()));
			updateemp.executeUpdate();

			updateemp.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
