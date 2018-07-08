//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.model;

import com.jfinal.plugin.activerecord.Model;
import com.zcurd.common.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class MenuDatarule extends Model<MenuDatarule> {
	private static final long serialVersionUID = 1L;
	public static final MenuDatarule me = new MenuDatarule();

	public MenuDatarule() {
	}

	public List<MenuDatarule> findAll() {
		return this.find("select * from sys_menu_datarule");
	}

	public List<MenuDatarule> findByUser(SysUser user) {
		List<MenuDatarule> result = new ArrayList();
		String roles = user.getStr("roles");
		if (StringUtil.isNotEmpty(roles)) {
			result = this.find("select distinct b.* from sys_role_datarule a join sys_menu_datarule b on a.datarule_id=b.id where a.role_id in(" + roles + ")");
		}

		return (List)result;
	}

	public String getFieldName() {
		return (String)this.get("field_name");
	}

	public String getSymbol() {
		return (String)this.get("symbol");
	}

	public String getValue() {
		return (String)this.get("value");
	}
}
