//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.controller;

import com.zcurd.model.Menu;

public class MenuController extends BaseController {
	public MenuController() {
	}

	public void listAll() {
		this.renderJson(Menu.me.findAll());
	}

	public void list() {
		this.render("list.html");
	}

	public void addPage() {
		this.render("add.html");
	}

	public void add() {
		if (((Menu)this.getModel(Menu.class, "model")).save()) {
			this.addOpLog("[菜单管理] 增加");
			this.renderSuccess();
		} else {
			this.renderFailed();
		}

	}

	public void updatePage() {
		this.setAttr("model", Menu.me.findById(this.getParaToInt("id")));
		this.render("update.html");
	}

	public void update() {
		if (((Menu)this.getModel(Menu.class, "model")).update()) {
			this.addOpLog("[菜单管理] 修改");
			this.renderSuccess();
		} else {
			this.renderFailed();
		}

	}

	public void delete() {
		Integer[] ids = this.getParaValuesToInt("id[]");
		Integer[] var5 = ids;
		int var4 = ids.length;

		for(int var3 = 0; var3 < var4; ++var3) {
			Integer id = var5[var3];
			Menu.me.deleteById(id);
		}

		this.addOpLog("[菜单管理] 删除");
		this.renderSuccess();
	}
}
