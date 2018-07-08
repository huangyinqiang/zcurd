//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.model;

import com.jfinal.plugin.activerecord.Model;
import com.zcurd.common.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class Menu extends Model<Menu> {
	private static final long serialVersionUID = 1L;
	public static final Menu me = new Menu();
	private List<Menu> subMenuList = new ArrayList();

	public Menu() {
	}

	public List<Menu> findAll() {
		return this.find("select * from sys_menu order by order_num asc");
	}

	public List<Menu> findByUser(SysUser user) {
		List<Menu> result = new ArrayList();
		String roles = user.getStr("roles");
		if (StringUtil.isNotEmpty(roles)) {
			result = this.find("select distinct b.* from sys_role_menu a join sys_menu b on a.menu_id=b.id where a.role_id in(" + roles + ")");
		}

		return (List)result;
	}

	public List<Menu> getSubMenuList() {
		return this.subMenuList;
	}

	public void setSubMenuList(List<Menu> subMenuList) {
		this.subMenuList = subMenuList;
	}
}
