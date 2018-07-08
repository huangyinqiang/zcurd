//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Table;
import com.jfinal.plugin.activerecord.TableMapping;
import com.zcurd.common.DbMetaTool;
import java.util.List;
import java.util.Map;

public class SysUser extends Model<SysUser> {
	private static final long serialVersionUID = 1L;
	public static final SysUser me = new SysUser();

	public SysUser() {
	}

	public List<SysUser> findAll() {
		return this.find("select * from sys_user order by create_time asc");
	}

	public List<SysUser> findByPid(long id) {
		return this.find("select * from sys_user where pid=" + id + " order by create_time asc");
	}

	public List<SysUser> findByAutoPay() {
		return this.find("select * from sys_user where autopay=0 order by create_time asc");
	}

	public List<SysUser> findByMultiProperties(String[] properties, Object[] values) {
		StringBuffer sql = new StringBuffer("select * from " + this.getTable().getName() + " where 1=1");
		if (properties != null) {
			String[] var7 = properties;
			int var6 = properties.length;

			for(int var5 = 0; var5 < var6; ++var5) {
				String property = var7[var5];
				sql.append(" and " + property + "=?");
			}
		}

		if (values == null) {
			values = new Object[0];
		}

		return this.find(sql.toString(), values);
	}

	public int getId() {
		return this.get("id") instanceof Long ? this.getLong("id").intValue() : this.getInt("id");
	}

	public String getUserName() {
		return (String)this.get("user_name");
	}

	private Table getTable() {
		return TableMapping.me().getTable(this.getUsefulClass());
	}

	public Map<String, Object> getDictDataroles() {
		return DbMetaTool.getDictData("select id, role_name from sys_role");
	}

	private Class<? extends Model> getUsefulClass() {
		Class c = this.getClass();
		return c.getName().indexOf("EnhancerByCGLIB") == -1 ? c : c.getSuperclass();
	}
}
