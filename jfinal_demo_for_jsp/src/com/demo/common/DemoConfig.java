package com.demo.common;

import com.demo.blog.BlogController;
import com.demo.blog.Data0073;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

/**
 * API引导式配置
 */
public class DemoConfig extends JFinalConfig {
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		loadPropertyFile("init_config.txt");				// 加载少量必要配置，随后可用getProperty(...)获取值
		me.setDevMode(getPropertyToBoolean("devMode", false));
		me.setViewType(ViewType.JSP); 							// 设置视图类型为Jsp，否则默认为FreeMarker
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", CommonController.class);
		me.add("/blog", BlogController.class);
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		C3p0Plugin cp = new C3p0Plugin(getProperty("jdbcUrl"),getProperty("user"), getProperty("password").trim());
		//配置Oracle驱动
		cp. setDriverClass("oracle.jdbc.driver.OracleDriver");
		me.add(cp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		me.add(arp);
		// 配置Oracle方言
		arp.setDialect(new OracleDialect());
		// 配置属性名(字段名)大小写不敏感容器工厂
		arp.setContainerFactory(new CaseInsensitiveContainerFactory());
		arp.addMapping("data0073", "PKEY", Data0073.class);
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("path"));
	}
	
	/**
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}
}
