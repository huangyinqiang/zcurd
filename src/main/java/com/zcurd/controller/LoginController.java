//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.controller;

import com.jfinal.aop.Duang;
import com.jfinal.captcha.CaptchaRender;
import com.zcurd.model.Menu;
import com.zcurd.model.SysUser;
import com.zcurd.service.LoginService;
import java.util.List;

public class LoginController extends BaseController {
	public LoginController() {
	}

	public void index() {
		this.render("login.html");
	}

	public void getMenu() {
		if ("admin".equals(this.getSessionUser().get("user_name"))) {
			this.renderJson(Menu.me.findAll());
		} else {
			Object menuList = this.getSessionAttr("menuList");
			this.renderJson(menuList);
		}

	}

	public void image() {
		this.renderCaptcha();
	}

	public void login() {
		LoginService loginService = (LoginService)Duang.duang(LoginService.class);
		String code = this.getPara("code");
		boolean flag = CaptchaRender.validate(this, code);
		if (!flag) {
			this.renderFailed("验证码错误！");
		} else {
			String name = this.getPara("user_name");
			String password = this.getPara("password");
			List<SysUser> list = SysUser.me.findByMultiProperties(new String[]{"user_name", "password"}, new Object[]{name, password});
			if (list.size() > 0) {
				this.setSessionAttr("sysUser", list.get(0));
				this.setSessionAttr("menuList", loginService.getUserMenu(this.getSessionUser()));
				List<String> noAuthUrl = loginService.getNoAuthUrl();
				this.setSessionAttr("noAuthUrl", noAuthUrl);
				this.setSessionAttr("noAuthBtnUrl", loginService.getNoAuthBtnUrl(this.getSessionUser()));
				this.setSessionAttr("noAuthDatarule", loginService.getNoAuthDatarule(this.getSessionUser()));
				if ("admin".equals(this.getSessionUser().get("user_name"))) {
					this.setSessionAttr("noAuthUrl", (Object)null);
					this.setSessionAttr("noAuthBtnUrl", (Object)null);
					this.setSessionAttr("pageBtnMap", (Object)null);
					this.setSessionAttr("noAuthDatarule", (Object)null);
				}

				this.addOpLog("登陆系统");
				this.renderSuccess();
			} else {
				this.renderFailed("用户名或密码错误！");
			}

		}
	}

	public void logout() {
		this.addOpLog("退出系统");
		this.removeSessionAttr("sysUser");
		this.redirect("/login");
	}
}
