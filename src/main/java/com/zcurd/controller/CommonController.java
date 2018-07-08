//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.controller;

import com.jfinal.upload.UploadFile;
import com.zcurd.common.DBTool;
import com.zcurd.common.util.StringUtil;
import com.zcurd.model.CommonFile;

public class CommonController extends BaseController {
	public CommonController() {
	}

	public void getDictData() {
		Object[] queryParams = this.getQueryParams();
		String[] properties = (String[])queryParams[0];
		String[] symbols = (String[])queryParams[1];
		Object[] values = (Object[])queryParams[2];
		String orderBy = this.getOrderBy();
		if (StringUtil.isEmpty(orderBy)) {
			orderBy = "id desc";
		}

		this.renderJson(DBTool.findByMultProperties("sys_dict", properties, symbols, values));
	}

	public void iconsPage() {
		this.render("common/icons.html");
	}

	public void uploadFile() {
		UploadFile file = this.getFile("upload", "images", 5242880);
		if (file != null) {
			((CommonFile)((CommonFile)((CommonFile)(new CommonFile()).set("type", 1)).set("path", file.getFileName())).set("sys_user_id", this.getSessionUser().get("id"))).save();
			this.renderJson("/upload/images/" + file.getFileName());
		} else {
			this.renderFailed();
		}

	}
}
