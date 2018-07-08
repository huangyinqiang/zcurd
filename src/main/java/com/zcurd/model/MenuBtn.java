//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.model;

import com.jfinal.plugin.activerecord.Model;
import com.zcurd.common.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class MenuBtn extends Model<MenuBtn> {
	private static final long serialVersionUID = 1L;
	public static final MenuBtn me = new MenuBtn();

	public MenuBtn() {
	}

	public List<MenuBtn> findAll() {
		return this.find("select * from sys_menu_btn");
	}

	public List<MenuBtn> findByUser(SysUser user) {
		List<MenuBtn> result = new ArrayList();
		String roles = user.getStr("roles");
		if (StringUtil.isNotEmpty(roles)) {
			result = this.find("select distinct b.* from sys_role_btn a join sys_menu_btn b on a.btn_id=b.id where a.role_id in(" + roles + ")");
		}

		return (List)result;
	}
}
