//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.common;

import com.busi.controller.CardlogController;
import com.busi.controller.ChargeBatteryInfoController;
import com.busi.controller.ChargeBatterySumController;
import com.busi.controller.ChargeController;
import com.busi.controller.ChargeMoneyInfoController;
import com.busi.controller.ChargeMoneySumController;
import com.busi.controller.Config2Controller;
import com.busi.controller.ConfigController;
import com.busi.controller.Customer2Controller;
import com.busi.controller.CustomerController;
import com.busi.controller.LostCardController;
import com.busi.controller.PayLogController;
import com.busi.controller.PayMoneySumController;
import com.busi.controller.QrMatchDeviceController;
import com.busi.controller.TuserController;
import com.busi.model.Cardlog;
import com.busi.model.Charge;
import com.busi.model.ChargeBatteryInfo;
import com.busi.model.ChargeMoneyInfo;
import com.busi.model.Config;
import com.busi.model.Customer;
import com.busi.model.LostCard;
import com.busi.model.Pay;
import com.busi.model.QrMatchDevice;
import com.busi.model.Tuser;
import com.busi.model.TuserCharge;
import com.busi.model.mq.ActiveMQ;
import com.busi.model.mq.ActiveMQPlugin;
import com.busi.model.mq.Destination;
import com.busi.model.mq.JmsSender;
import com.jfinal.aop.Duang;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import com.zcurd.common.handler.ZcurdHandler;
import com.zcurd.common.interceptor.AuthInterceptor;
import com.zcurd.common.interceptor.ErrorInterceptor;
import com.zcurd.controller.CommonController;
import com.zcurd.controller.LoginController;
import com.zcurd.controller.MainController;
import com.zcurd.controller.MenuController;
import com.zcurd.controller.RoleController;
import com.zcurd.controller.SysOplogController;
import com.zcurd.controller.SysUserController;
import com.zcurd.controller.TaskBaseController;
import com.zcurd.controller.ZcurdController;
import com.zcurd.controller.ZcurdHeadController;
import com.zcurd.model.CommonFile;
import com.zcurd.model.Menu;
import com.zcurd.model.MenuBtn;
import com.zcurd.model.MenuDatarule;
import com.zcurd.model.SysMenuBtn;
import com.zcurd.model.SysOplog;
import com.zcurd.model.SysUser;
import com.zcurd.model.TaskBase;
import com.zcurd.model.TaskLog;
import com.zcurd.model.ZcurdField;
import com.zcurd.model.ZcurdHead;
import com.zcurd.model.ZcurdHeadBtn;
import com.zcurd.model.ZcurdHeadJs;
import com.zcurd.service.TaskService;
import freemarker.template.TemplateModelException;
import javax.jms.JMSException;

public class ZcurdConfig extends JFinalConfig {
	public ZcurdConfig() {
	}

	public void configConstant(Constants me) {
		PropKit.use("config.property");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.FREE_MARKER);
	}

	public void configRoute(Routes me) {
		me.add("/customer", CustomerController.class, "/busi/customer");
		me.add("/customer2", Customer2Controller.class, "/busi/customer");
		me.add("/tuser", TuserController.class, "/busi/tuser");
		me.add("/weixin", ChargeBatteryInfoController.class, "/busi/weixin");
		me.add("/weixinsum", ChargeBatterySumController.class, "/busi/weixinsum");
		me.add("/money", ChargeMoneyInfoController.class, "/busi/chargemoney");
		me.add("/moneysum", ChargeMoneySumController.class, "/busi/chargemoneysum");
		me.add("/pay", PayMoneySumController.class, "/busi/pay");
		me.add("/paylog", PayLogController.class, "/busi/paylog");
		me.add("/cardlog", CardlogController.class, "/busi/cardlog");
		me.add("/charge", ChargeController.class, "/busi/charge");
		me.add("/config", ConfigController.class, "/busi/config");
		me.add("/config2", Config2Controller.class, "/busi/config");
		me.add("/device", QrMatchDeviceController.class, "/busi/device");
		me.add("/lostcard", LostCardController.class, "/busi/lostcard");
		me.add("/login", LoginController.class, "/zcurd/login");
		me.add("/zcurd", ZcurdController.class, "/zcurd/zcurd");
		me.add("/zcurdHead", ZcurdHeadController.class, "/zcurd");
		me.add("/menu", MenuController.class, "/zcurd/menu");
		me.add("/main", MainController.class, "/zcurd");
		me.add("/role", RoleController.class, "/zcurd/role");
		me.add("/common", CommonController.class, "/zcurd");
		me.add("/oplog", SysOplogController.class, "/zcurd/sysOplog");
		me.add("/user", SysUserController.class, "/zcurd/sysUser");
		me.add("/task", TaskBaseController.class, "/zcurd/taskBase");
	}

	public void configPlugin(Plugins me) {
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("base_jdbcUrl"), PropKit.get("base_user"), PropKit.get("base_password").trim());
		me.add(c3p0Plugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin("zcurd_base", c3p0Plugin);
		arp.setShowSql(true);
		me.add(arp);
		arp.addMapping("zcurd_head", ZcurdHead.class);
		arp.addMapping("zcurd_field", ZcurdField.class);
		arp.addMapping("zcurd_head_btn", ZcurdHeadBtn.class);
		arp.addMapping("zcurd_head_js", ZcurdHeadJs.class);
		arp.addMapping("sys_menu", Menu.class);
		arp.addMapping("sys_menu_btn", MenuBtn.class);
		arp.addMapping("sys_menu_datarule", MenuDatarule.class);
		arp.addMapping("sys_user", SysUser.class);
		arp.addMapping("sys_menu_btn", SysMenuBtn.class);
		arp.addMapping("sys_oplog", SysOplog.class);
		arp.addMapping("common_file", CommonFile.class);
		arp.addMapping("task_base", TaskBase.class);
		arp.addMapping("task_log", "id", TaskLog.class);
		C3p0Plugin c3p0PluginAir = new C3p0Plugin(PropKit.get("busi_jdbcUrl"), PropKit.get("busi_user"), PropKit.get("busi_password").trim());
		me.add(c3p0PluginAir);
		ActiveRecordPlugin arpAir = new ActiveRecordPlugin("zcurd_busi", c3p0PluginAir);
		arpAir.setShowSql(true);
		arpAir.addMapping("customer", Customer.class);
		arpAir.addMapping("charge", Charge.class);
		arpAir.addMapping("tuser", Tuser.class);
		arpAir.addMapping("tuser_charge", TuserCharge.class);
		arpAir.addMapping("lostcard", LostCard.class);
		arpAir.addMapping("config", Config.class);
		arpAir.addMapping("charge_info_view", ChargeBatteryInfo.class);
		arpAir.addMapping("qr_match_device", QrMatchDevice.class);
		arpAir.addMapping("cardlog", Cardlog.class);
		arpAir.addMapping("charge_money_info", ChargeMoneyInfo.class);
		arpAir.addMapping("pay_log", Pay.class);
		me.add(arpAir);
		ActiveMQPlugin p = new ActiveMQPlugin(PropKit.get("mq.address"));
		p.start();

		try {
			ActiveMQ.addSender(new JmsSender("WexinSender", ActiveMQ.getConnection(), Destination.Queue, PropKit.get("weixin.sender")));
		} catch (JMSException var8) {
			var8.printStackTrace();
		}

	}

	public void configInterceptor(Interceptors me) {
		me.add(new SessionInViewInterceptor());
		me.add(new AuthInterceptor());
		me.add(new ErrorInterceptor());
	}

	public void configHandler(Handlers me) {
		me.add(new ZcurdHandler());
	}

	public void afterJFinalStart() {
		try {
			FreeMarkerRender.getConfiguration().setSharedVariable("basePath", JFinal.me().getContextPath());
		} catch (TemplateModelException var2) {
			var2.printStackTrace();
		}

		TaskService taskService = (TaskService)Duang.duang(TaskService.class);
		taskService.startAll();
	}

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 8080, "/", 5);
	}

	public void configEngine(Engine me) {
	}
}
