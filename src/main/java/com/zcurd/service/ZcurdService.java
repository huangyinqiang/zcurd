//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.service;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.ICallback;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.zcurd.common.DBTool;
import com.zcurd.common.DbMetaTool;
import com.zcurd.common.ZcurdConst;
import com.zcurd.common.ZcurdTool;
import com.zcurd.common.util.FreemarkUtil;
import com.zcurd.common.util.StringUtil;
import com.zcurd.model.SysUser;
import com.zcurd.model.ZcurdField;
import com.zcurd.model.ZcurdHead;
import com.zcurd.vo.ZcurdMeta;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class ZcurdService {
	public ZcurdService() {
	}

	public void add(int headId, Map<String, String[]> paraMap, HttpServletRequest request, SysUser user) {
		ZcurdMeta mapmeta = this.getMetaData(headId);
		ZcurdHead head = mapmeta.getHead();
		List<ZcurdField> addFieldList = mapmeta.getAddFieldList();
		Record record = new Record();
		Iterator var10 = addFieldList.iterator();

		while(var10.hasNext()) {
			ZcurdField field = (ZcurdField)var10.next();
			String[] paramValues = (String[])paraMap.get("model." + field.getStr("field_name"));
			record.set(field.getStr("field_name"), paramValues == null ? null : paramValues[0]);
		}

		Map<String, Object> varData = new HashMap();
		varData.put("user", user);
		varData.put("metaData", mapmeta);
		varData.put("request", request);
		varData.put("session", request.getSession());
		this.defFieldHandle(record, mapmeta.getFieldList(), ZcurdConst.getSystemDefField4Add(user));
		Iterator var14 = mapmeta.getFieldList().iterator();

		while(var14.hasNext()) {
			ZcurdField field = (ZcurdField)var14.next();
			String defaultValue = field.getStr("default_value");
			if (StringUtil.isNotEmpty(defaultValue) && StringUtil.isEmpty(record.getStr(field.getStr("field_name")))) {
				record.set(field.getStr("field_name"), FreemarkUtil.parse(defaultValue, varData));
			}
		}

		Db.use(ZcurdTool.getDbSource(head.getDbSource())).save(head.getStr("table_name"), head.getStr("id_field"), record);
	}

	public void update(int headId, int id, Map<String, String[]> paraMap, SysUser user) {
		ZcurdMeta mapmeta = this.getMetaData(headId);
		ZcurdHead head = mapmeta.getHead();
		List<ZcurdField> updateFieldList = mapmeta.getUpdateFieldList();
		Record record = this.get(headId, id);
		Iterator var10 = updateFieldList.iterator();

		while(var10.hasNext()) {
			ZcurdField field = (ZcurdField)var10.next();
			if (field.getInt("is_allow_update") == 1) {
				String[] paramValues = (String[])paraMap.get("model." + field.getStr("field_name"));
				record.set(field.getStr("field_name"), paramValues == null ? null : paramValues[0]);
			}
		}

		this.defFieldHandle(record, mapmeta.getFieldList(), ZcurdConst.getSystemDefField4Update(user));
		Db.use(ZcurdTool.getDbSource(head.getDbSource())).update(head.getStr("table_name"), head.getStr("id_field"), record);
	}

	public void delete(int headId, Integer[] ids) {
		ZcurdMeta mapmeta = this.getMetaData(headId);
		ZcurdHead head = mapmeta.getHead();
		Integer[] var8 = ids;
		int var7 = ids.length;

		for(int var6 = 0; var6 < var7; ++var6) {
			Integer id = var8[var6];
			Db.use(ZcurdTool.getDbSource(head.getDbSource())).deleteById(head.getStr("table_name"), head.getStr("id_field"), new Object[]{id});
		}

		DbMetaTool.updateMetaData(headId);
	}

	public List<Map<String, Object>> getFooter(ZcurdMeta mapmeta, String[] properties, String[] symbols, Object[] values) {
		List<Map<String, Object>> footer = new ArrayList();
		ZcurdHead head = mapmeta.getHead();
		List<ZcurdField> footFieldList = mapmeta.getFooterFieldList();
		if (footFieldList.size() > 0) {
			StringBuilder sql = new StringBuilder("select ");

			for(int i = 0; i < footFieldList.size(); ++i) {
				ZcurdField field = (ZcurdField)footFieldList.get(i);
				if (i > 0) {
					sql.append(",");
				}

				sql.append(" sum(" + field.getStr("field_name") + ")");
			}

			sql.append(" from " + head.getTableName());
			List<Object> list = DBTool.findDbSource(head.getDbSource(), sql.toString(), properties, symbols, values);
			Object[] result = list.toArray(new Object[0]);
			if (footFieldList.size() > 1) {
				result = (Object[])list.get(0);
			}

			Map<String, Object> sumMap = new HashMap();

			for(int i = 0; i < footFieldList.size(); ++i) {
				sumMap.put(((ZcurdField)footFieldList.get(i)).getStr("field_name"), "<span style='color:blue;'>合计：" + result[i] + "</span>");
			}

			footer.add(sumMap);
		}

		return footer;
	}

	public Record get(int headId, int id) {
		ZcurdMeta mapmeta = this.getMetaData(headId);
		ZcurdHead head = mapmeta.getHead();
		Record record = Db.use(ZcurdTool.getDbSource(head.getDbSource())).findById(head.getStr("table_name"), head.getStr("id_field"), new Object[]{id});
		return record;
	}

	public ZcurdMeta getMetaData(int headId) {
		return DbMetaTool.getMetaData(headId);
	}

	public ZcurdHead getHead(int headId) {
		return (ZcurdHead)ZcurdHead.me.findById(headId);
	}

	public void genForm(final String tableName, String dbSource) {
		final String dbs = ZcurdTool.getDbSource(dbSource);
		Db.use(dbs).execute(new ICallback() {
			public Object call(Connection conn) throws SQLException {
				DatabaseMetaData metaData = conn.getMetaData();
				String dbName = conn.getCatalog();
				ResultSet pkRSet = metaData.getPrimaryKeys(dbName, (String)null, tableName);
				String form_name = tableName;
				String idField = null;
				if (pkRSet.next()) {
					try {
						String sql = "select TABLE_COMMENT from information_schema.TABLES a where a.TABLE_SCHEMA=? and a.table_name=?";
						form_name = Db.queryStr(sql, new Object[]{dbName, tableName});
					} catch (Exception var15) {
						System.out.println("获得表注释失败！" + var15.getMessage());
					}

					idField = (String)pkRSet.getObject(4);
				}

				ZcurdHead head = (ZcurdHead)((ZcurdHead)((ZcurdHead)(new ZcurdHead()).set("table_name", tableName)).set("form_name", form_name)).set("id_field", idField);
				head.set("db_source", dbs);
				head.save();
				ResultSet colRet = metaData.getColumns(dbName, "%", tableName, "%");
				int orderNum = 2;

				while(colRet.next()) {
					String field_name = colRet.getString("COLUMN_NAME");
					String column_name = colRet.getString("REMARKS");
					if (idField == null) {
						idField = field_name;
					}

					if (StringUtil.isEmpty(column_name)) {
						column_name = field_name;
					}

					ZcurdField field = (ZcurdField)((ZcurdField)((ZcurdField)((ZcurdField)((ZcurdField)((ZcurdField)(new ZcurdField()).set("head_id", head.getLong("id").intValue())).set("field_name", field_name)).set("column_name", column_name)).set("data_type", colRet.getString("TYPE_NAME").toLowerCase())).set("order_num", orderNum)).set("is_allow_null", colRet.getInt("NULLABLE"));
					++orderNum;
					if (field_name.equals(head.getIdField())) {
						field.set("order_num", 1);
						--orderNum;
					}

					String dataType = field.getStr("data_type");
					String inputType = "easyui-textbox";
					if (!dataType.equals("timestamp") && !dataType.equals("date") && !dataType.equals("datetime")) {
						if (dataType.equals("text")) {
							inputType = "textarea";
						} else if (dataType.endsWith("int") || dataType.equals("long")) {
							inputType = "easyui-numberspinner";
						}
					} else {
						inputType = "easyui-datebox";
					}

					field.set("input_type", inputType);
					field.save();
				}

				if (head.get("id_field") == null) {
					((ZcurdHead)head.set("id_field", idField)).update();
				}

				return null;
			}
		});
	}

	public void defFieldHandle(Record record, List<ZcurdField> fieldList, Object[][] defFields) {
		Object[][] var7 = defFields;
		int var6 = defFields.length;

		for(int var5 = 0; var5 < var6; ++var5) {
			Object[] sysDefField = var7[var5];
			Iterator var9 = fieldList.iterator();

			while(var9.hasNext()) {
				ZcurdField field = (ZcurdField)var9.next();
				if (field.getFieldName().equals(sysDefField[0].toString())) {
					record.set(sysDefField[0].toString(), sysDefField[1]);
				}
			}
		}

	}

	public static void main(String[] args) {
		C3p0Plugin c3p0Plugin = new C3p0Plugin("jdbc:mysql://127.0.0.1/zcurd?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull", "root", "123456");
		ActiveRecordPlugin arp = new ActiveRecordPlugin("zcurd", c3p0Plugin);
		c3p0Plugin.start();
		arp.start();
		String tableName = "blog";
		Db.use("zcurd").execute(new ICallback() {
			public Object call(Connection conn) throws SQLException {
				DatabaseMetaData metaData = conn.getMetaData();
				System.out.println("数据库：" + conn.getCatalog());
				ResultSet tableSet = metaData.getTables((String)null, "%", "%", new String[]{"TABLE"});

				while(tableSet.next()) {
					System.out.println(tableSet.getString("TABLE_NAME") + "\t" + tableSet.getString("REMARKS"));
				}

				ResultSet colRet = metaData.getColumns((String)null, "%", "blog", "%");

				while(colRet.next()) {
					String columnName = colRet.getString("COLUMN_NAME");
					String columnType = colRet.getString("TYPE_NAME");
					int datasize = colRet.getInt("COLUMN_SIZE");
					int digits = colRet.getInt("DECIMAL_DIGITS");
					int nullable = colRet.getInt("NULLABLE");
					System.out.println("字段：" + columnName + "\t" + columnType + "\t" + datasize + "\t" + digits + "\t" + nullable + "\t" + colRet.getString("REMARKS"));
				}

				ResultSet pkRSet = metaData.getPrimaryKeys((String)null, (String)null, "blog");

				while(pkRSet.next()) {
					System.err.println("****** Comment ******");
					System.err.println("TABLE_CAT : " + pkRSet.getObject(1));
					System.err.println("TABLE_SCHEM: " + pkRSet.getObject(2));
					System.err.println("TABLE_NAME : " + pkRSet.getObject(3));
					System.err.println("COLUMN_NAME: " + pkRSet.getObject(4));
					System.err.println("KEY_SEQ : " + pkRSet.getObject(5));
					System.err.println("PK_NAME : " + pkRSet.getObject(6));
					System.err.println("****** ******* ******");
				}

				return null;
			}
		});
	}
}
