package com.minitwit.model;

public class Item {
	private int id;
	private String name;
	private String des;
	private String image;
	private String content;
	private String date;
	private int publish;
	private int itemorder;
	private float price;
	private String params;
	private int groupid;
	private int grouptype;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPublish() {
		return publish;
	}

	public void setPublish(int publish) {
		this.publish = publish;
	}

	public int getItemorder() {
		return itemorder;
	}

	public void setItemorder(int itemorder) {
		this.itemorder = itemorder;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(int grouptype) {
		this.grouptype = grouptype;
	}

	public Item(int id, String name, String des, String image, String content, String date, int publish, int itemorder,
			float price, String params, int groupid, int grouptype) {
		super();
		this.id = id;
		this.name = name;
		this.des = des;
		this.image = image;
		this.content = content;
		this.date = date;
		this.publish = publish;
		this.itemorder = itemorder;
		this.price = price;
		this.params = params;
		this.groupid = groupid;
		this.grouptype = grouptype;
	}

	public Item() {
		super();
	}

}
