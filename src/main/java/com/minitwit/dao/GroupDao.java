package com.minitwit.dao;

import java.util.List;

import com.minitwit.model.Group;

public interface GroupDao {
	List<Group> listGroup(int type);
	
	Group getGroup(int groupid);
	
	void insertGroup(Group group);
	
	void deleteGroup(Group group);
	
	void editGroup(Group group);
}
