//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.common;

import com.jfinal.plugin.activerecord.Record;
import com.zcurd.common.util.StringUtil;
import com.zcurd.model.ZcurdField;
import com.zcurd.model.ZcurdHead;
import com.zcurd.model.ZcurdHeadBtn;
import com.zcurd.model.ZcurdHeadJs;
import com.zcurd.vo.ZcurdMeta;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DbMetaTool {
	private static Map<Integer, ZcurdMeta> metaDataMap = new Hashtable();

	public DbMetaTool() {
	}

	public static ZcurdMeta getMetaData(int headId) {
		ZcurdMeta metaData = (ZcurdMeta)metaDataMap.get(headId);
		if (metaData == null) {
			metaData = getMetaDataFromDb(headId);
			metaDataMap.put(headId, metaData);
		}

		return metaData;
	}

	private static ZcurdMeta getMetaDataFromDb(int headId) {
		ZcurdHead head = (ZcurdHead)ZcurdHead.me.findById(headId);
		List<ZcurdField> fieldList = ZcurdField.me.findByHeadId(head.getLong("id").intValue());
		Map<String, Map<String, Object>> dictMap = new HashMap();
		Iterator var5 = fieldList.iterator();

		while(var5.hasNext()) {
			ZcurdField zcurdField = (ZcurdField)var5.next();
			String dictSql = zcurdField.getStr("dict_sql");
			if (StringUtil.isNotEmpty(dictSql)) {
				Map<String, Object> dictData = getDictData(dictSql);
				dictMap.put(zcurdField.getStr("field_name"), dictData);
				zcurdField.put("dict", dictData);
			}
		}

		List<ZcurdField> addFieldList = new ArrayList();
		List<ZcurdField> updateFieldList = new ArrayList();
		List<ZcurdField> footerFieldList = new ArrayList();
		Iterator var8 = fieldList.iterator();

		while(var8.hasNext()) {
			ZcurdField zcurdField = (ZcurdField)var8.next();
			if (zcurdField.getIsShowList() == 1 && zcurdField.getIsSum() == 1) {
				footerFieldList.add(zcurdField);
			}

			if (!zcurdField.getStr("field_name").equals(head.getStr("id_field"))) {
				if (zcurdField.getInt("is_allow_add") == 1) {
					addFieldList.add(zcurdField);
				}

				if (zcurdField.getInt("is_allow_update") == 1 || zcurdField.getInt("is_allow_detail") == 1) {
					updateFieldList.add(zcurdField);
				}
			}
		}

		List<ZcurdHeadBtn> btnList = ZcurdHeadBtn.me.findByHeadId(headId);
		List<ZcurdHeadBtn> topList = new ArrayList();
		List<ZcurdHeadBtn> lineList = new ArrayList();
		Iterator var11 = btnList.iterator();

		ZcurdHeadBtn btn;
		while(var11.hasNext()) {
			btn = (ZcurdHeadBtn)var11.next();
			if (btn.getInt("location") == 1) {
				topList.add(btn);
			} else if (btn.getInt("location") == 2) {
				lineList.add(btn);
			}
		}

		var11 = btnList.iterator();

		while(var11.hasNext()) {
			btn = (ZcurdHeadBtn)var11.next();
			if (btn.getInt("action") == 1) {
				head.set("form_type", 2);
				break;
			}
		}

		List<ZcurdHeadJs> jsList = ZcurdHeadJs.me.findByHeadId(headId);
		ZcurdMeta zcurdMeta = new ZcurdMeta();
		zcurdMeta.setHead(head);
		zcurdMeta.setFieldList(fieldList);
		zcurdMeta.setDictMap(dictMap);
		zcurdMeta.setAddFieldList(addFieldList);
		zcurdMeta.setUpdateFieldList(updateFieldList);
		zcurdMeta.setFooterFieldList(footerFieldList);
		zcurdMeta.setBtnList(btnList);
		zcurdMeta.setTopList(topList);
		zcurdMeta.setLineList(lineList);
		zcurdMeta.setJsList(jsList);
		return zcurdMeta;
	}

	public static void updateMetaData(int headId) {
		metaDataMap.remove(headId);
	}

	public static Map<String, Object> getDictData(String dictSql) {
		String[] parseSql = DBTool.parseSQL4DbSource(dictSql);
		Map<String, Object> map = new LinkedHashMap();
		List<Record> listRecord = DBTool.use(parseSql[0]).find("select 'key', 'text' union all select * from (" + parseSql[1] + ") a");

		for(int i = 1; i < listRecord.size(); ++i) {
			Record record = (Record)listRecord.get(i);
			map.put(record.getStr("key"), record.getStr("text"));
		}

		return map;
	}
}
