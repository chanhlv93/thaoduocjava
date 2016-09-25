package com.minitwit.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.minitwit.config.DatabaseConfig;
import com.minitwit.dao.GroupDao;
import com.minitwit.model.Group;

@Repository
public class GroupDaoImpl implements GroupDao {
	
	Connection c = DatabaseConfig.GetDBSource();
	
	@Override
	public void insertGroup(Group group) {
		// TODO Auto-generated method stub
		try {
			Statement stmt = c.createStatement();
			String sqlQuery = "INSERT INTO tbgroup(name, des, image, grouptype, params) VALUES ('"+group.getName()+"','"
						+group.getDes()+"','"
						+group.getImage()+"',"
						+group.getGrouptype()+",'"
						+group.getParams()+"')";
			stmt.executeUpdate(sqlQuery);

		    stmt.close();
		    //c.commit();
		    //c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	@Override
	public void deleteGroup(Group group) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editGroup(Group group) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Group> listGroup(int type) {
		// TODO Auto-generated method stub
		List<Group> list = new ArrayList<Group>();
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM tbgroup WHERE grouptype ="+type+";");

			 while ( rs.next() ) {
				 Group group = new Group();
				 group.setId(rs.getInt("id"));
				 group.setName(rs.getString("name"));
				 group.setDes(rs.getString("des"));
				 group.setImage(rs.getString("des"));
				 group.setGrouptype(rs.getInt("grouptype"));
				 group.setParams(rs.getString("params"));
				 list.add(group);
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
	public Group getGroup(int groupid) {
		Group group = new Group();
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM tbgroup WHERE id ="+groupid+";");

			 while ( rs.next() ) {
				 group.setId(rs.getInt("id"));
				 group.setName(rs.getString("name"));
				 group.setDes(rs.getString("des"));
				 group.setImage(rs.getString("des"));
				 group.setGrouptype(rs.getInt("grouptype"));
				 group.setParams(rs.getString("params"));
		      }
		      rs.close();
		      stmt.close();
		      //c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + "::: " + e.getMessage());
			//System.exit(0);
		}

		return group;
	}

}
