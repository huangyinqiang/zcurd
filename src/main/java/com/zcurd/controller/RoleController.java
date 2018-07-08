//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.controller;

import com.jfinal.aop.Duang;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.zcurd.model.Menu;
import com.zcurd.model.SysMenuBtn;
import com.zcurd.service.RoleService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleController extends BaseController {
	public RoleController() {
	}

	public void editAuthPage() {
		int roleId = this.getParaToInt("roleId");
		this.setAttr("roleId", roleId);
		List<Record> btnIds = Db.find("select b.menu_id, b.id from sys_role_btn a join sys_menu_btn b on a.btn_id=b.id where role_id=?", new Object[]{roleId});
		List<Record> dataruleIds = Db.find("select b.menu_id, b.id from sys_role_datarule a join sys_menu_datarule b on a.datarule_id=b.id where role_id=?", new Object[]{roleId});
		this.setAttr("btnIds", btnIds);
		this.setAttr("dataruleIds", dataruleIds);
		this.render("editAuth.html");
	}

	public void editAuth() {
		String menuIds = this.getPara("menuIds");
		String btnIds = this.getPara("btnIds");
		String dataruleIds = this.getPara("dataruleIds");
		int roleId = this.getParaToInt("roleId");
		RoleService roleService = (RoleService)Duang.duang(RoleService.class);
		roleService.saveAuth(menuIds, btnIds, dataruleIds, roleId);
		this.addOpLog("[权限管理] 修改");
		this.renderSuccess();
	}

	public void getAllMenu() {
		int roleId = this.getParaToInt("roleId", 0);
		Map<String, Object> result = new HashMap();
		result.put("menuIds", Db.find("select * from sys_role_menu where role_id=?", new Object[]{roleId}));
		result.put("menuList", Menu.me.findAll());
		this.renderJson(result);
	}

	public void genAuthBtnBatch() {
		int menuId = this.getParaToInt("menuId");
		List<SysMenuBtn> modelList = new ArrayList();
		modelList.add((SysMenuBtn)((SysMenuBtn)((SysMenuBtn)((SysMenuBtn)(new SysMenuBtn()).set("menu_id", menuId)).set("btn_name", "增加")).set("class_name", "addBtn")).set("method_name", "add,addPage"));
		modelList.add((SysMenuBtn)((SysMenuBtn)((SysMenuBtn)((SysMenuBtn)(new SysMenuBtn()).set("menu_id", menuId)).set("btn_name", "修改")).set("class_name", "updateBtn")).set("method_name", "update,updatePage"));
		modelList.add((SysMenuBtn)((SysMenuBtn)((SysMenuBtn)((SysMenuBtn)(new SysMenuBtn()).set("menu_id", menuId)).set("btn_name", "删除")).set("class_name", "delBtn")).set("method_name", "delete"));
		modelList.add((SysMenuBtn)((SysMenuBtn)((SysMenuBtn)((SysMenuBtn)(new SysMenuBtn()).set("menu_id", menuId)).set("btn_name", "导出")).set("class_name", "exportBtn")).set("method_name", "exportCsv"));
		Db.batchSave(modelList, 1000);
		this.renderSuccess();
	}
}
