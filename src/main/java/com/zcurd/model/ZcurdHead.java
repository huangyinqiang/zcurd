//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.model;

import com.jfinal.plugin.activerecord.Model;

public class ZcurdHead extends Model<ZcurdHead> {
	private static final long serialVersionUID = 1L;
	public static final ZcurdHead me = new ZcurdHead();

	public ZcurdHead() {
	}

	public String getTableName() {
		return this.getStr("table_name");
	}

	public String getIdField() {
		return this.getStr("id_field");
	}

	public String getDbSource() {
		return this.getStr("db_source");
	}

	public String getFormName() {
		return this.getStr("form_name");
	}
}
