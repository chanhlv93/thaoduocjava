package com.minitwit.dao;

import java.util.List;

import com.minitwit.model.Item;

public interface ItemDao {
	List<Item> listItem(int groupType);
	
	List<Item> listItemCate(int groupId);
	
	Item getItem(Item item);
	
	void insertItem(Item item);
	
	void deleteItem(Item item);
	
	void editItem(Item item);
}
