package com.demo.blog;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * BlogController
 * 注意：在实际项目中业务与sql需要写在Model中，此demo仅为示意,故将sql写在了Controller中
 */
@Before(BlogInterceptor.class)
public class BlogController extends Controller {
	
	public void index() {
		setAttr("blogList", Data0073.dao.find("select * from Data0073 where div_code='GME' order by user_id asc"));
		render("blog.jsp");
//		System.out.println(pp.getPageNumber());
//		renderJson("P123456:123");
	}
	
	public void users(){
		render("/users/usersManage.jsp");
	}
	
	public void getUser(){
		int pageNumber = getParaToInt("page");
		int pageSize = getParaToInt("rows");
//		List list = Data0073.dao.find("select USER_ID,USER_LOGIN_NAME,USER_FULL_NAME from Data0073 where div_code='GME' and dept_code='GME IT'");
		Page<Data0073> list = Data0073.dao.paginate(pageNumber, pageSize, "select USER_ID,USER_LOGIN_NAME,USER_FULL_NAME","from Data0073 where div_code='GME' and dept_code='GME IT'");
		setAttr("rows",list.getList());
		setAttr("total",list.getTotalRow());
		renderJson(new String[]{"total","rows"});
//		renderJson("rows",list);
//		renderJson("{\"USER_LOGIN_NAME\":\"zhangzheng\",\"USER_ID\":\"0000001587\",\"USER_FULL_NAME\":\"张政\"},{\"USER_LOGIN_NAME\":\"zhongyu\",\"USER_ID\":\"000000500\",\"USER_FULL_NAME\":\"钟瑜 s\"},{\"USER_LOGIN_NAME\":\"yangyi\",\"USER_ID\":\"00000126\",\"USER_FULL_NAME\":\"杨轶\"}");
	}
	
//	public void add() {
//	}
//	
//	@Before(BlogValidator.class)
//	public void save() {
//		getModel(Blog.class).save();
//		redirect("/blog");
//	}
//	
//	public void edit() {
//		setAttr("blog", Blog.dao.findById(getParaToInt()));
//	}
//	
//	@Before(BlogValidator.class)
//	public void update() {
//		getModel(Blog.class).update();
//		redirect("/blog");
//	}
//	
//	public void delete() {
//		Blog.dao.deleteById(getParaToInt());
//		redirect("/blog");
//	}
}


