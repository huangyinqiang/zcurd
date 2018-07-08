//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.model;

import com.jfinal.plugin.activerecord.Model;
import com.zcurd.common.DbMetaTool;
import java.util.Map;

public class SysMenuBtn extends Model<SysMenuBtn> {
	private static final long serialVersionUID = 1L;
	public static final SysMenuBtn me = new SysMenuBtn();

	public SysMenuBtn() {
	}

	public Map<String, Object> getDictDatamenu_id() {
		return DbMetaTool.getDictData("select id, menu_name from sys_menu");
	}
}
