//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.controller;

import com.jfinal.plugin.activerecord.Db;
import com.zcurd.common.util.PasswordUtil;
import com.zcurd.model.SysUser;

public class SysUserController extends BaseController {
	public SysUserController() {
	}

	public void updatePasswordPage() {
		this.setAttr("dictDataroles", SysUser.me.getDictDataroles());
		this.setAttr("model", SysUser.me.findById(this.getSessionUser().get("id")));
		this.render("updatePassword.html");
	}

	public void updatePassword() {
		SysUser model = (SysUser)SysUser.me.findById(this.getSessionUser().get("id"));
		if (!model.getStr("password").equals(PasswordUtil.encodePassword(this.getPara("model.old_password")))) {
			this.renderFailed("原始密码输入错误");
		} else {
			model.set("password", PasswordUtil.encodePassword(this.getPara("model.password")));
			model.update();
			this.addOpLog("[用户行为] 修改密码");
			this.renderSuccess();
		}
	}

	public void resetPassword() {
		Db.update("update sys_user set password='" + PasswordUtil.encodePassword("123456") + "' where id=" + this.getParaToInt("id"));
		this.addOpLog("[系统用户] 重置密码");
		this.renderSuccess();
	}
}
