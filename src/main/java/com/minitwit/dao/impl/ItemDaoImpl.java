package com.minitwit.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.minitwit.config.DatabaseConfig;
import com.minitwit.dao.ItemDao;
import com.minitwit.model.Item;

@Repository
public class ItemDaoImpl implements ItemDao {
	
	final int CATE_ARTICLE = 1;
	final int CATE_MENU = 2;
	final int CATE_LEFTBAR = 3;
	final int CATE_TAG = 4;
	final int CATE_FOOTER = 5;
	
	private GroupDaoImpl groupImpl;
	
	Connection c = DatabaseConfig.GetDBSource();

	private int getIdInsert(long timeInsert) {
		int idInsert = 0;
		try {
			Statement stmt = c.createStatement();
			String sqlQuery = "Select id from tbitem where date = '" + timeInsert + "'";
			ResultSet rs = stmt.executeQuery(sqlQuery);

			while (rs.next()) {
				idInsert = rs.getInt("id");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return idInsert;
	}
	
	@Override
	public List<Item> listItem(int grouptype) {
		List<Item> list = new ArrayList<Item>();
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM tbitem WHERE grouptype = " + grouptype +" ORDER BY itemorder ASC;");
			
			 while ( rs.next() ) {
				 Item item = new Item();
				 item.setId(rs.getInt("id"));
				 item.setName(rs.getString("name"));
				 item.setDes(rs.getString("des"));
				 item.setImage(rs.getString("image"));
				 item.setContent(rs.getString("content"));
				 item.setItemorder(rs.getInt("itemorder"));
				 item.setGroupid(rs.getInt("groupid"));
				 item.setGrouptype(rs.getInt("grouptype"));
				 item.setPublish(rs.getInt("publish"));
				 
				 list.add(item);
		      }
		      rs.close();
		      stmt.close();
		      //c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + "::: " + e.getMessage());
			//System.exit(0);
		}

		return list;
	}
	
	@Override
	public void insertItem(Item item) {
		// TODO Auto-generated method stub
		try {
			Statement stmt = c.createStatement();
			Date date = new Date();
			long datetime = date.getTime();
			String sqlQuery = "INSERT INTO tbitem(name, des, image, content, grouptype, groupid, date, itemorder, publish) VALUES ('" + item.getName() + "','"
					+ item.getDes() + "','" + item.getImage() + "','" + item.getContent() + 
					"'," + item.getGrouptype() + "," + item.getGroupid() + 
					",'" + datetime + "'," + item.getItemorder() +","+item.getPublish()+")";
			stmt.executeUpdate(sqlQuery);
			stmt.close();
			// c.commit();
			// c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	@Override
	public void deleteItem(Item item) {
		// TODO Auto-generated method stub
		try {
			Statement stmt = c.createStatement();
			String sqlQuery = "DELETE FROM tbitem WHERE id = " + item.getId() + ";";
			stmt.executeUpdate(sqlQuery);
			stmt.close();
			// c.commit();
			// c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	@Override
	public void editItem(Item item) {
		// TODO Auto-generated method stub
		try {
			Statement stmt = c.createStatement();
			String sqlQuery = "UPDATE tbitem "
					+ "SET name = '" +item.getName()+ "',des = '" + item.getDes()+"', image = '" + item.getImage()
					+ "', content = '" + item.getContent() + "', groupid = " + item.getGroupid()
					+ ", itemorder = " + item.getItemorder() + ", grouptype = " + item.getGrouptype()
					+ " WHERE id = " + item.getId();
			stmt.executeUpdate(sqlQuery);
			stmt.close();
			// c.commit();
			// c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	@Override
	public Item getItem(Item item) {
		// TODO Auto-generated method stub
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM tbitem WHERE id = " + item.getId() +";");
			
			 while ( rs.next() ) {
				 item.setId(rs.getInt("id"));
				 item.setName(rs.getString("name"));
				 item.setDes(rs.getString("des"));
				 item.setImage(rs.getString("image"));
				 item.setContent(rs.getString("content"));
				 item.setItemorder(rs.getInt("itemorder"));
				 item.setGroupid(rs.getInt("groupid"));
				 item.setGrouptype(rs.getInt("grouptype"));
				 item.setPublish(rs.getInt("publish"));
		      }
		      rs.close();
		      stmt.close();
		      //c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + "::: " + e.getMessage());
		}
		return item;
	}

	@Override
	public List<Item> listItemCate(int groupId) {
		List<Item> list = new ArrayList<Item>();
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM tbitem WHERE groupid = " + groupId +";");
			
			 while ( rs.next() ) {
				 Item item = new Item();
				 item.setId(rs.getInt("id"));
				 item.setName(rs.getString("name"));
				 item.setDes(rs.getString("des"));
				 item.setImage(rs.getString("image"));
				 item.setContent(rs.getString("content"));
				 item.setItemorder(rs.getInt("itemorder"));
				 item.setGroupid(rs.getInt("groupid"));
				 item.setGrouptype(rs.getInt("grouptype"));
				 item.setPublish(rs.getInt("publish"));
				 
				 list.add(item);
		      }
		      rs.close();
		      stmt.close();
		      //c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + "::: " + e.getMessage());
			//System.exit(0);
		}

		return list;
	}
}
