//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.zcurd.common.util.StringUtil;

public class RoleService {
	public RoleService() {
	}

	@Before({Tx.class})
	public void saveAuth(String menuIds, String btnIds, String dataruleIds, int roleId) {
		Db.update("delete from sys_role_menu where role_id=?", new Object[]{roleId});
		String dataruleId;
		int var6;
		int var7;
		String[] var8;
		if (StringUtil.isNotEmpty(menuIds)) {
			var7 = (var8 = menuIds.split(",")).length;

			for(var6 = 0; var6 < var7; ++var6) {
				dataruleId = var8[var6];
				Db.update("INSERT INTO sys_role_menu (role_id, menu_id) VALUES (?, ?)", new Object[]{roleId, dataruleId});
			}
		}

		Db.update("delete from sys_role_btn where role_id=?", new Object[]{roleId});
		if (StringUtil.isNotEmpty(btnIds)) {
			var7 = (var8 = btnIds.split(",")).length;

			for(var6 = 0; var6 < var7; ++var6) {
				dataruleId = var8[var6];
				Db.update("INSERT INTO sys_role_btn (role_id, btn_id) VALUES (?, ?)", new Object[]{roleId, dataruleId});
			}
		}

		Db.update("delete from sys_role_datarule where role_id=?", new Object[]{roleId});
		if (StringUtil.isNotEmpty(dataruleIds)) {
			var7 = (var8 = dataruleIds.split(",")).length;

			for(var6 = 0; var6 < var7; ++var6) {
				dataruleId = var8[var6];
				Db.update("INSERT INTO sys_role_datarule (role_id, datarule_id) VALUES (?, ?)", new Object[]{roleId, dataruleId});
			}
		}

	}
}
