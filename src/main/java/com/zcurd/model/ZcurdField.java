//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import java.util.List;

public class ZcurdField extends Model<ZcurdField> {
	private static final long serialVersionUID = 1L;
	public static final ZcurdField me = new ZcurdField();

	public ZcurdField() {
	}

	public int getIsShowList() {
		return this.getInt("is_show_list");
	}

	public int getIsSum() {
		return this.getInt("is_sum");
	}

	public String getFieldName() {
		return (String)this.get("field_name");
	}

	public void setFieldName(String value) {
		this.set("field_name", value);
	}

	public Page<ZcurdField> paginate(int pageNumber, int pageSize, int headId) {
		return this.paginate(pageNumber, pageSize, "select * ", "from zcurd_field where head_id=? order by order_num", new Object[]{headId});
	}

	public List<ZcurdField> findByHeadId(int headId) {
		return this.find("select * from zcurd_field where head_id=? order by order_num", new Object[]{headId});
	}
}
