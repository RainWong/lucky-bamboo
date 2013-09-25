package com.rainwong.users;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * UserController
 * 注意：在实际项目中业务与sql�?��写在Model中，此demo仅为示意,故将sql写在了Controller�?
 */
@Before(UserInterceptor.class)
public class UserController extends Controller {
	
	public void index() {
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
	}
	
//	public void add() {
//	}
//	
//	@Before(UserValidator.class)
//	public void save() {
//		getModel(Blog.class).save();
//		redirect("/blog");
//	}
//	
//	public void edit() {
//		setAttr("blog", Blog.dao.findById(getParaToInt()));
//	}
//	
//	@Before(UserValidator.class)
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


