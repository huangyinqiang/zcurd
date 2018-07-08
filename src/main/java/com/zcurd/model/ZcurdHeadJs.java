//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.model;

import com.jfinal.plugin.activerecord.Model;
import java.util.List;

public class ZcurdHeadJs extends Model<ZcurdHeadJs> {
	private static final long serialVersionUID = 1L;
	public static final ZcurdHeadJs me = new ZcurdHeadJs();
	private String jsContentData;

	public ZcurdHeadJs() {
	}

	public List<ZcurdHeadJs> findByHeadId(int headId) {
		List<ZcurdHeadJs> list = this.find("select * from zcurd_head_js where head_id=?", new Object[]{headId});
		return list;
	}

	public String getJsContentData() {
		return this.jsContentData;
	}

	public void setJsContentData(String jsContentData) {
		this.jsContentData = jsContentData;
	}
}
