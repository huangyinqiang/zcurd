//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.controller;

import com.jfinal.aop.Duang;
import com.jfinal.plugin.activerecord.Record;
import com.zcurd.common.DBTool;
import com.zcurd.common.DbMetaTool;
import com.zcurd.common.ZcurdTool;
import com.zcurd.common.handler.CurdHandle;
import com.zcurd.common.util.FreemarkUtil;
import com.zcurd.common.util.StringUtil;
import com.zcurd.ext.render.csv.CsvRender;
import com.zcurd.model.ZcurdField;
import com.zcurd.model.ZcurdHead;
import com.zcurd.model.ZcurdHeadJs;
import com.zcurd.service.ZcurdService;
import com.zcurd.vo.ZcurdMeta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ZcurdController extends BaseController {
	public ZcurdController() {
	}

	public void listPage() {
		int headId = this.getHeadId();
		ZcurdService zcurdService = (ZcurdService)Duang.duang(ZcurdService.class);
		ZcurdMeta metaData = zcurdService.getMetaData(headId);
		this.flushDictData(metaData);
		this.setAttr("sort", this.getPara("sort"));
		this.setAttr("order", this.getPara("order"));
		this.setAttr("headId", headId);
		this.setAttrs(metaData.toMap());
		this.setAttr("queryPara", ZcurdTool.getQueryPara(this.getParaMap()));
		this.handleVar(metaData, (Record)null);
		this.render("listPage.html");
	}

	public void listData() {
		int headId = this.getHeadId();
		ZcurdService zcurdService = (ZcurdService)Duang.duang(ZcurdService.class);
		ZcurdMeta metaData = DbMetaTool.getMetaData(headId);
		ZcurdHead head = metaData.getHead();
		Object[] queryParams = this.getQueryParams();
		String[] properties = (String[])queryParams[0];
		String[] symbols = (String[])queryParams[1];
		Object[] values = (Object[])queryParams[2];
		String orderBy = this.getOrderBy();
		if (StringUtil.isEmpty(orderBy)) {
			orderBy = head.getIdField() + " desc";
		}

		this.renderDatagrid(ZcurdTool.replaceDict(metaData, DBTool.findByMultPropertiesDbSource(head.getDbSource(), head.getTableName(), properties, symbols, values, orderBy, this.getPager())), DBTool.countByMultPropertiesDbSource(head.getDbSource(), head.getTableName(), properties, symbols, values), zcurdService.getFooter(metaData, properties, symbols, values));
	}

	public void addPage() {
		int headId = this.getHeadId();
		ZcurdService zcurdService = (ZcurdService)Duang.duang(ZcurdService.class);
		ZcurdMeta metaData = zcurdService.getMetaData(headId);
		this.flushDictData(metaData);
		Map<String, Object> varData = new HashMap();
		varData.put("user", this.getSessionUser());
		varData.put("metaData", metaData);
		varData.put("request", this.getRequest());
		varData.put("session", this.getRequest().getSession());
		Iterator var6 = metaData.getAddFieldList().iterator();

		while(var6.hasNext()) {
			ZcurdField field = (ZcurdField)var6.next();
			String defaultValue = field.getStr("default_value");
			if (StringUtil.isNotEmpty(defaultValue)) {
				field.set("default_value", FreemarkUtil.parse(defaultValue, varData));
			}
		}

		this.setAttr("headId", headId);
		this.setAttrs(metaData.toMap());
		this.setAttr("queryPara", ZcurdTool.getQueryPara(this.getParaMap()));
		this.handleVar(metaData, (Record)null);
	}

	public void add() {
		int headId = this.getHeadId();
		ZcurdMeta metaData = DbMetaTool.getMetaData(headId);
		ZcurdHead head = metaData.getHead();
		Map<String, String[]> paraMap = new HashMap();
		paraMap.putAll(this.getParaMap());
		String handleClass = head.getStr("handle_class");
		if (StringUtil.isNotEmpty(handleClass)) {
			try {
				CurdHandle ch = (CurdHandle)Duang.duang(Class.forName(handleClass));
				ch.add(metaData, this.getRequest(), paraMap);
			} catch (Exception var7) {
				var7.printStackTrace();
			}
		}

		ZcurdService zcurdService = (ZcurdService)Duang.duang(ZcurdService.class);
		zcurdService.add(this.getHeadId(), paraMap, this.getRequest(), this.getSessionUser());
		this.addOpLog("[" + head.getFormName() + "] 增加");
		this.renderSuccess();
	}

	public void updatePage() {
		int headId = this.getHeadId();
		ZcurdService zcurdService = (ZcurdService)Duang.duang(ZcurdService.class);
		ZcurdMeta metaData = zcurdService.getMetaData(headId);
		this.flushDictData(metaData);
		this.setAttr("headId", headId);
		this.setAttrs(metaData.toMap());
		Record currRecord = zcurdService.get(headId, this.getParaToInt("id"));
		this.setAttr("model", currRecord.getColumns());
		this.handleVar(metaData, currRecord);
		this.render("updatePage.html");
	}

	public void update() {
		int headId = this.getHeadId();
		ZcurdMeta metaData = DbMetaTool.getMetaData(headId);
		ZcurdHead head = metaData.getHead();
		Map<String, String[]> paraMap = new HashMap();
		paraMap.putAll(this.getParaMap());
		String handleClass = head.getStr("handle_class");
		if (StringUtil.isNotEmpty(handleClass)) {
			try {
				CurdHandle ch = (CurdHandle)Duang.duang(Class.forName(handleClass));
				ch.update(metaData, this.getRequest(), paraMap);
			} catch (Exception var7) {
				var7.printStackTrace();
			}
		}

		ZcurdService zcurdService = (ZcurdService)Duang.duang(ZcurdService.class);
		zcurdService.update(this.getHeadId(), this.getParaToInt("id"), paraMap, this.getSessionUser());
		this.addOpLog("[" + head.getFormName() + "] 修改");
		this.renderSuccess();
	}

	public void delete() {
		int headId = this.getHeadId();
		ZcurdMeta metaData = DbMetaTool.getMetaData(headId);
		ZcurdHead head = metaData.getHead();
		Integer[] ids = this.getParaValuesToInt("id[]");
		ZcurdService zcurdService = (ZcurdService)Duang.duang(ZcurdService.class);
		zcurdService.delete(this.getHeadId(), ids);
		this.addOpLog("[" + head.getFormName() + "] 删除");
		this.renderSuccess();
	}

	public void detailPage() {
		int headId = this.getHeadId();
		ZcurdMeta metaData = DbMetaTool.getMetaData(headId);
		ZcurdService zcurdService = (ZcurdService)Duang.duang(ZcurdService.class);
		Record row = zcurdService.get(headId, this.getParaToInt("id"));
		this.setAttr("headId", headId);
		this.setAttrs(metaData.toMap());
		this.setAttr("model", ZcurdTool.replaceDict(metaData, row));
		this.handleVar(metaData, row);
		this.render("detailPage.html");
	}

	public void exportCsv() {
		int headId = this.getHeadId();
		ZcurdMeta metaData = DbMetaTool.getMetaData(headId);
		ZcurdHead head = metaData.getHead();
		List<ZcurdField> fieldList = metaData.getFieldList();
		this.flushDictData(metaData);
		Object[] queryParams = this.getQueryParams();
		String[] properties = (String[])queryParams[0];
		String[] symbols = (String[])queryParams[1];
		Object[] values = (Object[])queryParams[2];
		String orderBy = this.getOrderBy();
		if (StringUtil.isEmpty(orderBy)) {
			orderBy = head.getIdField() + " desc";
		}

		List<Record> list = ZcurdTool.replaceDict(headId, DBTool.findByMultPropertiesDbSource(ZcurdTool.getDbSource(head.getDbSource()), head.getTableName(), properties, symbols, values));
		List<String> headers = new ArrayList();
		List<String> clomuns = new ArrayList();
		Iterator var14 = fieldList.iterator();

		while(var14.hasNext()) {
			ZcurdField zcurdField = (ZcurdField)var14.next();
			if (zcurdField.getInt("is_show_list") == 1) {
				headers.add(zcurdField.getStr("column_name"));
				clomuns.add(zcurdField.getStr("field_name"));
			}
		}

		CsvRender csvRender = new CsvRender(headers, list);
		csvRender.clomuns(clomuns);
		csvRender.fileName(head.getStr("form_name"));
		this.addOpLog("[" + head.getFormName() + "] 导出cvs");
		this.render(csvRender);
	}

	private int getHeadId() {
		String headId = (String)this.getAttr("headId");
		return Integer.parseInt(headId);
	}

	private void flushDictData(ZcurdMeta metaData) {
		Iterator var3 = metaData.getFieldList().iterator();

		while(var3.hasNext()) {
			ZcurdField zcurdField = (ZcurdField)var3.next();
			String dictSql = zcurdField.getStr("dict_sql");
			if (StringUtil.isNotEmpty(dictSql)) {
				Map<String, Object> dictData = DbMetaTool.getDictData(dictSql);
				metaData.getDictMap().put(zcurdField.getStr("field_name"), dictData);
				zcurdField.put("dict", dictData);
			}
		}

	}

	private void handleVar(ZcurdMeta metaData, Record currRecord) {
		Map<String, Object> varData = new HashMap();
		varData.put("currRecord", currRecord);
		varData.put("user", this.getSessionUser());
		varData.put("metaData", metaData);
		varData.put("request", this.getRequest());
		varData.put("session", this.getSession());
		List<Object> sqlData = new ArrayList();
		Iterator var6 = metaData.getJsList().iterator();

		while(true) {
			ZcurdHeadJs zcurdHeadJs;
			String sqlContent;
			do {
				if (!var6.hasNext()) {
					varData.put("sqlData", sqlData);
					var6 = metaData.getJsList().iterator();

					while(var6.hasNext()) {
						zcurdHeadJs = (ZcurdHeadJs)var6.next();
						zcurdHeadJs.setJsContentData(FreemarkUtil.parse(zcurdHeadJs.getStr("js_content"), varData));
					}

					return;
				}

				zcurdHeadJs = (ZcurdHeadJs)var6.next();
				sqlContent = FreemarkUtil.parse(zcurdHeadJs.getStr("sql_content"), varData);
			} while(!StringUtil.isNotEmpty(sqlContent));

			zcurdHeadJs.set("sql_content", sqlContent);
			String[] var11;
			int var10 = (var11 = sqlContent.split(";")).length;

			for(int var9 = 0; var9 < var10; ++var9) {
				String sql = var11[var9];
				sqlData.add(DBTool.findBySQL4DbSource(sql));
			}
		}
	}
}
