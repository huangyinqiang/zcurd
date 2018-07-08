//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Duang;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.ICallback;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.RenderException;
import com.zcurd.common.DBTool;
import com.zcurd.common.DbMetaTool;
import com.zcurd.common.util.StringUtil;
import com.zcurd.model.ZcurdField;
import com.zcurd.model.ZcurdHead;
import com.zcurd.service.ZcurdService;
import com.zcurd.vo.ZcurdMeta;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ZcurdHeadController extends BaseController {
	public ZcurdHeadController() {
	}

	public void list() {
		this.render("head/list.html");
	}

	public void listData() {
		Object[] queryParams = this.getQueryParams();
		String[] properties = (String[])queryParams[0];
		String[] symbols = (String[])queryParams[1];
		Object[] values = (Object[])queryParams[2];
		String orderBy = this.getOrderBy();
		if (StringUtil.isEmpty(orderBy)) {
			orderBy = "id desc";
		}

		this.renderDatagrid(DBTool.findByMultProperties("zcurd_head", properties, symbols, values, orderBy, this.getPager()), DBTool.countByMultProperties("zcurd_head", properties, symbols, values));
	}

	public void updatePage() {
		this.setAttr("model", ZcurdHead.me.findById(this.getParaToInt("id")));
		this.render("head/update.html");
	}

	public void update() {
		String fields = this.getPara("rowsStr");
		final JSONArray jsonObjs = JSONObject.parseArray(fields);
		Db.tx(new IAtom() {
			public boolean run() throws SQLException {
				ZcurdHead zcurdHead = (ZcurdHead)ZcurdHeadController.this.getModel(ZcurdHead.class, "model");
				zcurdHead.update();
				Long headId = zcurdHead.getLong("id");
				Db.update("delete from zcurd_field where head_id=" + headId);
				if (jsonObjs.size() > 0) {
					Iterator var4 = jsonObjs.iterator();

					while(var4.hasNext()) {
						Object object = var4.next();
						ZcurdField field = new ZcurdField();
						field.set("head_id", headId);
						field.put((Map)object);
						field.save();
					}
				}

				DbMetaTool.updateMetaData(headId.intValue());
				return true;
			}
		});
		this.addOpLog("[在线表单] 修改");
		this.renderSuccess("保存成功！");
	}

	public void genFormPage() {
		this.render("head/genForm.html");
	}

	public void genFormData() {
		String dbSource = this.getPara("db_source");
		String dbName = (String)DBTool.use(dbSource).execute(new ICallback() {
			public Object call(Connection conn) throws SQLException {
				return conn.getCatalog();
			}
		});
		String sql = "select TABLE_SCHEMA, TABLE_TYPE, a.TABLE_NAME, TABLE_COMMENT, CREATE_TIME from information_schema.TABLES a where a.TABLE_SCHEMA='" + dbName + "' order by CREATE_TIME desc";
		this.renderDatagrid(DBTool.use(dbSource).find(sql));
	}

	public void genForm() {
		String tableName = this.getPara("tableName");
		String dbSource = this.getPara("db_source");
		ZcurdService zcurdService = (ZcurdService)Duang.duang(ZcurdService.class);
		zcurdService.genForm(tableName, dbSource);
		this.addOpLog("[在线表单] 生成表单");
		this.renderSuccess();
	}

	public void delete() {
		Integer[] ids = this.getParaValuesToInt("id[]");
		Integer[] var5 = ids;
		int var4 = ids.length;

		for(int var3 = 0; var3 < var4; ++var3) {
			Integer id = var5[var3];
			ZcurdHead.me.deleteById(id);
			Db.update("delete from zcurd_field where head_id=?", new Object[]{id});
			DbMetaTool.updateMetaData(id);
		}

		this.addOpLog("[在线表单] 删除");
		this.renderSuccess();
	}

	public void listField() {
		this.renderDatagrid(ZcurdField.me.paginate(this.getParaToInt("page", 1), this.getParaToInt("rows", 500), this.getParaToInt("head_id")));
	}

	public void genCode() throws IOException, TemplateException {
		int headId = this.getParaToInt("headId");
		ZcurdService zcurdService = (ZcurdService)Duang.duang(ZcurdService.class);
		ZcurdMeta metaMap = zcurdService.getMetaData(headId);
		ZcurdHead head = metaMap.getHead();
		String tableName = head.getTableName();
		String className = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);

		String lowerClassName;
		for(int index = className.indexOf("_"); index > 0; index = className.indexOf("_")) {
			lowerClassName = className.substring(index + 1, index + 2);
			className = className.replace("_" + lowerClassName, lowerClassName.toUpperCase());
		}

		lowerClassName = className.substring(0, 1).toLowerCase() + className.substring(1);
		Map<String, Object> mateDate = metaMap.toMap();
		mateDate.put("className", className);
		mateDate.put("queryPara", new HashMap());
		this.copyTemp("listPage.html");
		this.copyTemp("addPage.html");
		this.copyTemp("updatePage.html");
		this.copyTemp("detailPage.html");
		String genCodePath = PropKit.get("genCodePath") + className + "/";
		String genCodePagePath = genCodePath + lowerClassName + "/";
		(new File(genCodePath)).mkdirs();
		(new File(genCodePagePath)).mkdirs();
		this.gen(mateDate, "/zcurd/zcurd/genCode/listPage.html", genCodePagePath + "list.html");
		this.gen(mateDate, "/zcurd/zcurd/genCode/addPage.html", genCodePagePath + "add.html");
		this.gen(mateDate, "/zcurd/zcurd/genCode/updatePage.html", genCodePagePath + "update.html");
		this.gen(mateDate, "/zcurd/zcurd/genCode/detailPage.html", genCodePagePath + "detail.html");
		this.gen(mateDate, "/zcurd/zcurd/genCode/controller.html", genCodePath + className + "Controller.java");
		this.gen(mateDate, "/zcurd/zcurd/genCode/model.html", genCodePath + className + ".java");
		this.addOpLog("[在线表单] 生成代码");
		this.renderSuccess("代码生成成功！保存在" + genCodePath);
	}

	private void gen(Map<String, Object> mateDate, String tempFile, String genFile) throws FileNotFoundException, UnsupportedEncodingException {
		Configuration config = FreeMarkerRender.getConfiguration();
		PrintWriter pw = new PrintWriter(new File(genFile), "utf8");

		try {
			Template template = config.getTemplate(tempFile);
			template.process(mateDate, pw);
		} catch (Exception var10) {
			throw new RenderException(var10);
		} finally {
			if (pw != null) {
				pw.close();
			}

		}

	}

	private void copyTemp(String fileName) {
		String basePath = PathKit.getWebRootPath() + "/zcurd/zcurd/";
		String content = StringUtil.readTxt2String(new File(basePath + fileName));
		content = content.replaceAll("<#include", "\\${\"<\"}#include");
		content = content.replace("<#list item.dict.keySet() as key>,{id:'${key}', text:'${item.dict.get(key)}'}</#list>", "<${'#'}list dictData${item.field_name}.keySet() as key>,{id:'${'$'}{key}', text:'${'$'}{dictData${item.field_name}.get(key)}'}</${'#'}list>");
		if ("addPage.html".equals(fileName) || "updatePage.html".equals(fileName) || "detailPage.html".equals(fileName)) {
			content = content.replace("\"headId\": ${headId},", "");
			content = content.replace("model[item.field_name]??", "1==1");
			content = content.replace("${model[head.id_field]}", "${'$'}{model.${head.id_field}}");
			content = content.replaceAll("\\$\\{model\\[item\\.field_name\\]", "\\${'\\$'}{model.\\${item.field_name}");
			content = content.replaceAll("\\$\\{modelDetail\\[item\\.field_name\\]", "\\${'\\$'}{modelDetail.\\${item.field_name}");
		}

		StringUtil.saveToFile(basePath + "genCode/" + fileName, content);
	}
}
