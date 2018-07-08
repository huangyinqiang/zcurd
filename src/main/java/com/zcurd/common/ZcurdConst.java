//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.common;

import com.zcurd.model.SysUser;
import java.util.Date;

public class ZcurdConst {
	public static final String ADMIN_SESSIOIN_USER_KEY = "sysUser";

	public ZcurdConst() {
	}

	public static Object[][] getSystemDefField4Add(SysUser user) {
		return new Object[][]{{"sys_create_user_id", user.getId()}, {"sys_create_user", user.getUserName()}, {"sys_create_time", new Date()}, {"sys_update_user_id", user.getId()}, {"sys_update_user", user.getUserName()}, {"sys_update_user_number", user.getUserName()}, {"sys_update_time", new Date()}};
	}

	public static Object[][] getSystemDefField4Update(SysUser user) {
		return new Object[][]{{"sys_update_user_id", user.getId()}, {"sys_update_user_number", user.getUserName()}, {"sys_update_user", user.getUserName()}, {"sys_update_time", new Date()}};
	}
}
