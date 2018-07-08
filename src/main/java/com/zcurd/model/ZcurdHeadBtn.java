//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.model;

import com.jfinal.plugin.activerecord.Model;
import java.util.List;

public class ZcurdHeadBtn extends Model<ZcurdHeadBtn> {
	private static final long serialVersionUID = 1L;
	public static final ZcurdHeadBtn me = new ZcurdHeadBtn();

	public ZcurdHeadBtn() {
	}

	public List<ZcurdHeadBtn> findByHeadId(int headId) {
		List<ZcurdHeadBtn> list = this.find("select * from zcurd_head_btn where head_id=?", new Object[]{headId});
		return list;
	}
}
