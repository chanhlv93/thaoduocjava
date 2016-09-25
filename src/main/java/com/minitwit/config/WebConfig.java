package com.minitwit.config;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.SparkBase.staticFileLocation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import com.minitwit.model.Group;
import com.minitwit.model.Item;
import com.minitwit.model.LoginResult;
import com.minitwit.model.User;
import com.minitwit.service.impl.MiniTwitService;
import com.minitwit.util.Utils;

import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;
import spark.utils.IOUtils;

public class WebConfig {

	final int CATE_ARTICLE = 1;
	final int CATE_MENU = 2;
	final int CATE_LEFTBAR = 3;
	final int CATE_TAG = 4;
	final int CATE_FOOTER = 5;
	
	final int MENU_PATH = 1;
	final int MENU_CONTENT = 2;
	
	private static final String USER_SESSION_ID = "user";
	private MiniTwitService service;	
	
	public WebConfig(MiniTwitService service) {
		this.service = service;
		staticFileLocation("/public/");
		setupRoutes();
	}

	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");		   
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	private void setupRoutes() {
		/*
		 * Shows a users timeline or if no user is logged in, it will redirect
		 * to the public timeline. This timeline shows the user's messages as
		 * well as all the messages of followed users.
		 */

		get("/admin", (req, res) -> {
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();
			List<Item> itemList = new ArrayList<Item>();
			itemList = service.listArticle(CATE_ARTICLE);
			
			List<Group> categoryList = new ArrayList<Group>();
			categoryList = service.listCategory(CATE_ARTICLE);
			
			map.put("pageTitle", "Thêm bài viết");
			map.put("activeMenu", "dashboard");
			map.put("user", user);
			map.put("itemList", itemList);
			map.put("categoryList", categoryList);
			return new ModelAndView(map, "admin.ftl");
		}, new FreeMarkerEngine());
		before("/admin", (req, res) -> {
			User user = getAuthenticatedUser(req);
			if (user == null) {
				res.redirect("/login");
				halt();
			}
		});

		get("/admin/addarticle", (req, res) -> {
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();
			List<Group> categoryList = new ArrayList<Group>();
			categoryList = service.listCategory(CATE_ARTICLE);
			map.put("pageTitle", "Thêm bài viết");
			map.put("activeMenu", "article");
			map.put("user", user);
			map.put("categoryList", categoryList);
			return new ModelAndView(map, "adminaddarticle.ftl");
		}, new FreeMarkerEngine());
		before("/admin/addarticle", (req, res) -> {
			User user = getAuthenticatedUser(req);
			if (user == null) {
				res.redirect("/login");
				halt();
			}
		});
		
		get("/admin/editarticle/:id", (req, res) -> {
			Item editItem = new Item();
			String idQueryString = req.params(":id");
			editItem.setId(Integer.parseInt(idQueryString));
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();
			List<Group> categoryList = new ArrayList<Group>();
			categoryList = service.listCategory(CATE_ARTICLE);
			editItem = service.getArticle(editItem);
			map.put("activeMenu", "article");
			map.put("user", user);
			map.put("editItem", editItem);
			map.put("categoryList", categoryList);
			return new ModelAndView(map, "admineditarticle.ftl");
		}, new FreeMarkerEngine());
		before("/admin/editarticle/:id", (req, res) -> {
			User user = getAuthenticatedUser(req);
			if (user == null) {
				res.redirect("/login");
				halt();
			}
		});
		
		post("/admin/editarticle/:id", (req, res) -> {
			MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
			req.raw().setAttribute("org.eclipse.multipartConfig", multipartConfigElement);
		    //Part file = req.raw().getPart("file"); //file is name of the upload form
			
			OutputStream out = null;
		    InputStream filecontent = null;
		    Item baiviet = new Item();
		    try {
		    	Part filePart = req.raw().getPart("file");
		    	Part articleId = req.raw().getPart("id");
		    	Part groupId = req.raw().getPart("groupid");
		    	Part name = req.raw().getPart("name");
		    	Part des = req.raw().getPart("des");
		    	Part content = req.raw().getPart("content");
		    	Part currentImage = req.raw().getPart("currentimage");
			    String fileName = getFileName(filePart);
			    int groupid = Integer.parseInt(IOUtils.toString(groupId.getInputStream()));
			    int artilceid = Integer.parseInt(IOUtils.toString(articleId.getInputStream()));
			    String articleName = IOUtils.toString(name.getInputStream());
			    String articleDes = IOUtils.toString(des.getInputStream());
			    String articleContent = IOUtils.toString(content.getInputStream());
			    String oldImage = IOUtils.toString(currentImage.getInputStream());
			    
			    if (articleName == "" || articleDes == "" || articleContent == "" || groupid == 0) {
			    	res.redirect("/admin/editarticle/"+artilceid);
			    	halt();
			    }
			    
			    if (fileName.length() > 0) {
			    	String fileNameUpload = Utils.AddNameImages(fileName);
			        out = new FileOutputStream(new File("src/main/resources/public/anh/" + fileNameUpload));
			        filecontent = filePart.getInputStream();
			        int read = 0;
			        final byte[] bytes = new byte[1024];
			        while ((read = filecontent.read(bytes)) != -1) {
			            out.write(bytes, 0, read);
			        }
			        filecontent.close();
			        out.close();
			        baiviet.setImage(fileNameUpload);
			    } else {
			    	baiviet.setImage(oldImage);
			    }
			    
		        baiviet.setId(artilceid);
			    baiviet.setGrouptype(CATE_ARTICLE);
			    baiviet.setGroupid(groupid);
			    baiviet.setName(articleName);
		    	baiviet.setDes(articleDes);		    	
		    	baiviet.setContent(articleContent);
				service.editArticle(baiviet);
				halt(200);
		        res.redirect("/admin");
		    } catch (FileNotFoundException fne) {
		    	res.redirect("/admin");
		        return null;
		    } finally {
		        if (out != null) {
		            out.close();
		        }
		        if (filecontent != null) {
		            filecontent.close();
		        }
		        res.redirect("/admin");
		    }
		    
		    return null;
		});
		

		post("/admin/addarticle", (req, res) -> {
			MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
			req.raw().setAttribute("org.eclipse.multipartConfig", multipartConfigElement);
		    //Part file = req.raw().getPart("file"); //file is name of the upload form
			
			OutputStream out = null;
		    InputStream filecontent = null;
		    Item baiviet = new Item();
		    try {
		    	Part filePart = req.raw().getPart("file");
		    	Part groupId = req.raw().getPart("groupid");
		    	Part name = req.raw().getPart("name");
		    	Part des = req.raw().getPart("des");
		    	Part content = req.raw().getPart("content");
			    String fileName = getFileName(filePart);
			    int groupid = Integer.parseInt(IOUtils.toString(groupId.getInputStream()));
			    String articleName = IOUtils.toString(name.getInputStream());
			    String articleDes = IOUtils.toString(des.getInputStream());
			    String articleContent = IOUtils.toString(content.getInputStream());
			    
			    if (fileName.length() == 0 || articleName == "" || articleDes == "" || articleContent == "" || groupid == 0) {
			    	res.redirect("/admin/addarticle");
			    	halt();
			    }
			    
			    String fileNameUpload = Utils.AddNameImages(fileName);

		        out = new FileOutputStream(new File("src/main/resources/public/anh/" + fileNameUpload));
		        filecontent = filePart.getInputStream();
		        int read = 0;
		        final byte[] bytes = new byte[1024];
		        while ((read = filecontent.read(bytes)) != -1) {
		            out.write(bytes, 0, read);
		        }
		        filecontent.close();
		        out.close();
			    baiviet.setGrouptype(CATE_ARTICLE);
			    baiviet.setGroupid(groupid);
			    baiviet.setName(articleName);
		    	baiviet.setDes(articleDes);
		    	baiviet.setImage(fileNameUpload);
		    	baiviet.setContent(articleContent);
				service.addArticle(baiviet);
				halt(200);
		        res.redirect("/admin");
		    } catch (FileNotFoundException fne) {
		    	res.redirect("/admin");
		        return null;
		    } finally {
		        if (out != null) {
		            out.close();
		        }
		        if (filecontent != null) {
		            filecontent.close();
		        }
		        res.redirect("/admin");
		    }
		    
		    return null;
		});

		get("/admin/deletearticle/:id", (req, res) -> {
			String idQueryString = req.params(":id");
			Item item = new Item();
			item.setId(Integer.parseInt(idQueryString));
			item = service.getArticle(item);
			if (item.getGrouptype() == CATE_ARTICLE) {
				service.deleteArticle(item);
			}
			res.redirect("/admin");
			halt();
			return null;
		});
		
		get("/admin/category", (req, res) -> {
			User user = getAuthenticatedUser(req);
			List<Group> categoryList = new ArrayList<Group>();
			categoryList = service.listCategory(CATE_ARTICLE);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Thêm bài viết");
			map.put("activeMenu", "article");
			map.put("user", user);
			map.put("categoryList", categoryList);

			return new ModelAndView(map, "addcategory.ftl");
		}, new FreeMarkerEngine());
		before("/admin", (req, res) -> {
			User user = getAuthenticatedUser(req);
			if (user == null) {
				res.redirect("/login");
				halt();
			}
		});
			
		post("/admin/addcategory", (req, res) -> {
			Group group = new Group();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8", -1);
				System.out.println("--->" + params);
				BeanUtils.populate(group, params);
				group.setGrouptype(CATE_ARTICLE);
				service.addCategory(group);
				res.redirect("/admin/category");
				// halt();
				return null;
			} catch (Exception e) {
				halt(501);
				return null;
			}
		});

		get("/admin/addmenu", (req, res) -> {
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();
			
			List<Item> menuList = new ArrayList<Item>();
			menuList = service.listArticle(CATE_MENU);
			
			List<Group> categoryList = new ArrayList<Group>();
			categoryList = service.listCategory(CATE_ARTICLE);
			
			map.put("pageTitle", "Thêm menu");
			map.put("user", user);
			map.put("activeMenu", "menu");
			map.put("categoryList", categoryList);
			map.put("menuList", menuList);
			return new ModelAndView(map, "addmenu.ftl");
		}, new FreeMarkerEngine());
		before("/admin", (req, res) -> {
			User user = getAuthenticatedUser(req);
			if (user == null) {
				res.redirect("/login");
				halt();
			}
		});
		
		get("/admin/editmenu/:id", (req, res) -> {
			String idQueryString = req.params(":id");
			int menuid = Integer.parseInt(idQueryString);
			
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();	
			Item menu = new Item();
			menu.setId(menuid);
			menu = service.getArticle(menu);
			List<Group> categoryList = new ArrayList<Group>();
			categoryList = service.listCategory(CATE_ARTICLE);
			map.put("user", user);
			map.put("activeMenu", "menu");
			map.put("categoryList", categoryList);
			map.put("menu", menu);
			return new ModelAndView(map, "admineditmenu.ftl");
		}, new FreeMarkerEngine());
		before("/admin", (req, res) -> {
			User user = getAuthenticatedUser(req);
			if (user == null) {
				res.redirect("/login");
				halt();
			}
		});
		
		get("/admin/deletemenu/:id", (req, res) -> {
			String idQueryString = req.params(":id");
			Item item = new Item();
			item.setId(Integer.parseInt(idQueryString));
			item = service.getArticle(item);
			if (item.getPublish() != 0) {
				service.deleteArticle(item);
			}
			res.redirect("/admin/addmenu");
			halt();
			return null;
		});
		
		post("/admin/addmenupath", (req, res) -> {
			Item menu = new Item();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8", -1);
				BeanUtils.populate(menu, params);
				menu.setGrouptype(CATE_MENU);
				String des = "/danh-muc/" + menu.getGroupid();
				menu.setDes(des);
				menu.setPublish(MENU_PATH);
				service.addArticle(menu);
				res.redirect("/admin/addmenu");
				// halt();
				return null;
			} catch (Exception e) {
				halt(501);
				return null;
			}
		});
		
		post("/admin/editmenupath", (req, res) -> {
			Item menu = new Item();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8", -1);
				BeanUtils.populate(menu, params);
				menu.setGrouptype(CATE_MENU);
				String des = "/danh-muc/" + menu.getGroupid();
				menu.setDes(des);
				menu.setPublish(MENU_PATH);
				service.editArticle(menu);
				res.redirect("/admin/addmenu");
				// halt();
				return null;
			} catch (Exception e) {
				halt(501);
				return null;
			}
		});
		
		post("/admin/addmenucontent", (req, res) -> {
			MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
			req.raw().setAttribute("org.eclipse.multipartConfig", multipartConfigElement);
		    //Part file = req.raw().getPart("file"); //file is name of the upload form
			
			OutputStream out = null;
		    InputStream filecontent = null;
		    Item baiviet = new Item();
		    try {
		    	Part filePart = req.raw().getPart("file");
		    	Part name = req.raw().getPart("name");
		    	Part des = req.raw().getPart("des");
		    	Part content = req.raw().getPart("content");
		    	Part itemorder = req.raw().getPart("itemorder");
			    String fileName = getFileName(filePart);
			    String orderString = IOUtils.toString(itemorder.getInputStream());
			    if (orderString.length() == 0) {
			    	orderString = "0";
			    }
			    int itemOrder = Integer.parseInt(orderString);
			    String articleName = IOUtils.toString(name.getInputStream());
			    String articleDes = IOUtils.toString(des.getInputStream());
			    String articleContent = IOUtils.toString(content.getInputStream());
			    String fileNameUpload = "";
			    
			    if (fileName.length() > 0) {
			    	fileNameUpload = Utils.AddNameImages(fileName);

			        out = new FileOutputStream(new File("src/main/resources/public/anh/" + fileNameUpload));
			        filecontent = filePart.getInputStream();
			        int read = 0;
			        final byte[] bytes = new byte[1024];
			        while ((read = filecontent.read(bytes)) != -1) {
			            out.write(bytes, 0, read);
			        }
			        filecontent.close();
			        out.close();
			    }
			    
			    baiviet.setGrouptype(CATE_MENU);
			    baiviet.setItemorder(itemOrder);
			    baiviet.setName(articleName);
		    	baiviet.setDes(articleDes);
		    	baiviet.setImage(fileNameUpload);
		    	baiviet.setContent(articleContent);
		    	baiviet.setPublish(MENU_CONTENT);
				service.addArticle(baiviet);
				halt(200);
		        res.redirect("/admin/addmenu");
		    } catch (FileNotFoundException fne) {
		    	res.redirect("//admin/addmenu");
		        return null;
		    } finally {
		        if (out != null) {
		            out.close();
		        }
		        if (filecontent != null) {
		            filecontent.close();
		        }
		        res.redirect("/admin/addmenu");
		    }
		    
		    return null;
		});
		
		post("/admin/editmenucontent", (req, res) -> {
			MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
			req.raw().setAttribute("org.eclipse.multipartConfig", multipartConfigElement);
		    //Part file = req.raw().getPart("file"); //file is name of the upload form
			
			OutputStream out = null;
		    InputStream filecontent = null;
		    Item baiviet = new Item();
		    try {
		    	Part filePart = req.raw().getPart("file");
		    	Part articleId = req.raw().getPart("id");
		    	Part groupId = req.raw().getPart("itemorder");
		    	Part name = req.raw().getPart("name");
		    	Part des = req.raw().getPart("des");
		    	Part content = req.raw().getPart("content");
		    	Part currentImage = req.raw().getPart("currentimage");
			    String fileName = getFileName(filePart);
			    int itemorder = Integer.parseInt(IOUtils.toString(groupId.getInputStream()));
			    int artilceid = Integer.parseInt(IOUtils.toString(articleId.getInputStream()));
			    String articleName = IOUtils.toString(name.getInputStream());
			    String articleDes = IOUtils.toString(des.getInputStream());
			    String articleContent = IOUtils.toString(content.getInputStream());
			    String oldImage = IOUtils.toString(currentImage.getInputStream());
			    
			    /*if (articleName == "" || articleDes == "" || articleContent == "" || groupid == 0) {
			    	res.redirect("/admin/editarticle/"+artilceid);
			    	halt();
			    }*/
			    
			    if (fileName.length() > 0) {
			    	String fileNameUpload = Utils.AddNameImages(fileName);
			        out = new FileOutputStream(new File("src/main/resources/public/anh/" + fileNameUpload));
			        filecontent = filePart.getInputStream();
			        int read = 0;
			        final byte[] bytes = new byte[1024];
			        while ((read = filecontent.read(bytes)) != -1) {
			            out.write(bytes, 0, read);
			        }
			        filecontent.close();
			        out.close();
			        baiviet.setImage(fileNameUpload);
			    } else {
			    	baiviet.setImage(oldImage);
			    }
			    
		        baiviet.setId(artilceid);
			    baiviet.setGrouptype(CATE_MENU);
			    baiviet.setItemorder(itemorder);
			    baiviet.setName(articleName);
		    	baiviet.setDes(articleDes);		    	
		    	baiviet.setContent(articleContent);
		    	baiviet.setPublish(MENU_CONTENT);
				service.editArticle(baiviet);
				halt(200);
		        res.redirect("/admin/addmenu");
		    } catch (FileNotFoundException fne) {
		    	res.redirect("/admin/addmenu");
		        return null;
		    } finally {
		        if (out != null) {
		            out.close();
		        }
		        if (filecontent != null) {
		            filecontent.close();
		        }
		        res.redirect("/admin/addmenu");
		    }
		    
		    return null;
		});
		
		get("/danh-muc/:id", (req, res) -> {
			String groupId = req.params(":id");
			int groupid = Integer.parseInt(groupId);
			Group cate = new Group();
			cate = service.getGroup(groupid);
			
			List<Item> menuList = new ArrayList<Item>();
			menuList = service.listArticle(CATE_MENU);
			
			Map<String, Object> map = new HashMap<>();
			List<Item> itemList =  new ArrayList<Item>();
			itemList = service.listArticleByGroup(groupid);
			map.put("cate", cate);
			map.put("itemList", itemList);
			map.put("menuList", menuList);
			return new ModelAndView(map, "clientcate.ftl");
		}, new FreeMarkerEngine());
		
		get("/san-pham/:id", (req, res) -> {
			String itemId = req.params(":id");
			int itemid = Integer.parseInt(itemId);
			Item item = new Item();
			item.setId(itemid);
			item = service.getArticle(item);
			
			List<Item> menuList = new ArrayList<Item>();
			menuList = service.listArticle(CATE_MENU);
			Map<String, Object> map = new HashMap<>();
			
			map.put("menuList", menuList);
			map.put("item", item);
			return new ModelAndView(map, "clientdetail.ftl");
		}, new FreeMarkerEngine());
		
		get("/", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			List<Item> menuList = new ArrayList<Item>();
			menuList = service.listArticle(CATE_MENU);
			map.put("pageTitle", "Timeline");
			map.put("menuList", menuList);
			return new ModelAndView(map, "home.ftl");
		}, new FreeMarkerEngine());

		get("/login", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			if (req.queryParams("r") != null) {
				map.put("message", "You were successfully registered and can login now");
			}
			return new ModelAndView(map, "login.ftl");
		}, new FreeMarkerEngine());

		post("/login", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			User user = new User();
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8", -1);
				BeanUtils.populate(user, params);
			} catch (Exception e) {
				halt(501);
				return null;
			}
			LoginResult result = service.checkUser(user);
			if (result.getUser() != null) {
				addAuthenticatedUser(req, result.getUser());
				user = getAuthenticatedUser(req);
				res.redirect("/admin");
				halt();
			} else {
				map.put("error", result.getError());
			}
			map.put("username", user.getUsername());
			return new ModelAndView(map, "login.ftl");
		}, new FreeMarkerEngine());

		get("/logout", (req, res) -> {
			removeAuthenticatedUser(req);
			res.redirect("/");
			return null;
		});

		get("/public", (req, res) -> {
			User user = getAuthenticatedUser(req);
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Public Timeline");
			map.put("user", user);

			return new ModelAndView(map, "timeline.ftl");
		}, new FreeMarkerEngine());

	}

	private void addAuthenticatedUser(Request request, User u) {
		request.session().attribute(USER_SESSION_ID, u);

	}

	private void removeAuthenticatedUser(Request request) {
		request.session().removeAttribute(USER_SESSION_ID);

	}

	private User getAuthenticatedUser(Request request) {
		return request.session().attribute(USER_SESSION_ID);
	}
}
