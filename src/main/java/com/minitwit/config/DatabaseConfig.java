package com.minitwit.config;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConfig {

	public static void InitDatabase() {
		// File file = new File("thaoduoc.db");
		File f = new File("thaoduoc.db");

		if (f.exists()) {
			System.out.println("File existed");
		} else {
			Connection c = null;
			Statement stmt = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:thaoduoc.db");
				System.out.println("Opened database successfully");

				stmt = c.createStatement();
				String sqlItem = "CREATE TABLE tbitem(id INTEGER PRIMARY KEY AUTOINCREMENT, name NVARCHAR, des NVARCHAR, image NVARCHAR, content TEXT, date NVARCHAR, publish INT, itemorder INT, price FLOAT, params NVARCHAR, grouptype int, groupid int);";
				String sqlGroup = "CREATE TABLE tbgroup(id INTEGER PRIMARY KEY AUTOINCREMENT, name NVARCHAR, des NVARCHAR, image NVARCHAR, grouptype int, params NVARCHAR);";
				String sqlGroupItem = "CREATE TABLE tbgroupitem(id INTEGER PRIMARY KEY AUTOINCREMENT, groupid int, itemid int, giorder int, params NVARCHAR);";
				String sqlUser = "CREATE TABLE tbuser(username VARCHAR, password VARCHAR);";				
				String sqlInsertDefaultUser = "INSERT INTO tbuser VALUES ('admin','$2a$10$5pMfhmagUqAg0gwM3FzwtOnQzH55Q1KYJAPaDCiKsers3MldnSxJq');";
				stmt.executeUpdate(sqlItem);
				stmt.executeUpdate(sqlGroup);
				stmt.executeUpdate(sqlGroupItem);
				stmt.executeUpdate(sqlUser);
				
				stmt.executeUpdate(sqlInsertDefaultUser);

				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}

			System.out.println("Table created successfully");
		}

	}

	public static Connection GetDBSource() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:thaoduoc.db");
			//c.setAutoCommit(false);
			System.out.println("Opened database successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return c;
	}
}
