package com.minitwit.service.impl;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minitwit.dao.GroupDao;
import com.minitwit.dao.ItemDao;
import com.minitwit.dao.UserDao;
import com.minitwit.model.Group;
import com.minitwit.model.Item;
import com.minitwit.model.LoginResult;
import com.minitwit.model.User;
import com.minitwit.util.PasswordUtil;

@Service
public class MiniTwitService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private ItemDao itemDao;
	
	public LoginResult checkUser(User user) {
		LoginResult result = new LoginResult();
		
		User userFound = userDao.getUserbyUsername(user.getUsername());
		if(userFound == null) {
			result.setError("Invalid username");
		} else if(!PasswordUtil.verifyPassword(user.getPassword(), userFound.getPassword())) {
			result.setError("Invalid password");
		} else {
			result.setUser(userFound);
		}
		
		return result;
	}
	
	public List<Group> listCategory(int type) {
		return groupDao.listGroup(type);
	}
	
	public void addCategory(Group group) {
		if (group.getImage() == null) {
			group.setImage("");
		}
		if (group.getParams() == null) {
			group.setParams("");
		}
		groupDao.insertGroup(group);
	}
	
	public Item getArticle(Item item) {
		return itemDao.getItem(item);
	}
	
	public void addArticle(Item item) {
		itemDao.insertItem(item);
	}
	
	public void editArticle(Item item) {
		itemDao.editItem(item);
	}
	
	public void deleteArticle(Item item) {
		itemDao.deleteItem(item);
	}
	
	public List<Item> listArticleByGroup(int groupId) {
		List<Group> listCategory = listCategory(1);
		List<Item> listItem = itemDao.listItemCate(groupId);
		
		for (Item item : listItem) {
			item.setParams(getCategoryName(item.getGroupid(), listCategory));
		}
		return listItem;
	}
	
	public List<Item> listArticle(int groupType) {
		List<Group> listCategory = listCategory(1);
		List<Item> listItem = itemDao.listItem(groupType);
		
		for (Item item : listItem) {
			item.setParams(getCategoryName(item.getGroupid(), listCategory));
		}
		return listItem;
	}
	
	public Group getGroup(int groupid) {
		return groupDao.getGroup(groupid);
	}
	
	public void registerUser(User user) {
		user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
		userDao.registerUser(user);
	}
	
	private String getCategoryName(int cateid, List<Group> groupList) {
		String cateName = null;
		for (Group group : groupList) {
			if (group.getId() == cateid) {
				cateName = group.getName();
				break;
			}
		}
		return cateName;
	}
}
