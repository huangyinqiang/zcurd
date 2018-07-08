//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.common;

import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.Record;
import com.zcurd.common.util.StringUtil;
import com.zcurd.vo.ZcurdMeta;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ZcurdTool {
	public ZcurdTool() {
	}

	public static Map<String, String> getQueryPara(Map<String, String[]> paraMap) {
		Map<String, String> queryPara = new HashMap();
		Iterator var3 = paraMap.keySet().iterator();

		while(var3.hasNext()) {
			String paraName = (String)var3.next();
			queryPara.put(paraName, ((String[])paraMap.get(paraName))[0]);
		}

		return queryPara;
	}

	public static Record replaceDict(ZcurdMeta metaData, Record record) {
		Map<String, Map<String, Object>> dictData = metaData.getDictMap();
		Iterator var4 = dictData.keySet().iterator();

		while(true) {
			String key;
			do {
				if (!var4.hasNext()) {
					return record;
				}

				key = (String)var4.next();
			} while(record.get(key) == null);

			String dictValueStr = "";
			String[] var9;
			int var8 = (var9 = record.get(key).toString().split(",")).length;

			for(int var7 = 0; var7 < var8; ++var7) {
				String fieldValue = var9[var7];
				Object dictValue = ((Map)dictData.get(key)).get(fieldValue);
				if (dictValue != null) {
					dictValueStr = dictValueStr + "," + dictValue.toString();
				} else {
					dictValueStr = dictValueStr + "," + fieldValue;
				}
			}

			record.set("_" + key, record.get(key));
			record.set(key, dictValueStr.substring(1));
		}
	}

	public static void replaceDict(Map<String, Object> dictData, Record record, String fieldName) {
		record.set("_" + fieldName, record.get(fieldName));
		if (record.get(fieldName) != null && dictData.get(record.get(fieldName).toString()) != null) {
			record.set(fieldName, dictData.get(record.get(fieldName).toString()));
		}

	}

	public static void replaceDict(Map<String, Object> dictData, List<Record> recordList, String fieldName) {
		Iterator var4 = recordList.iterator();

		while(var4.hasNext()) {
			Record record = (Record)var4.next();
			replaceDict(dictData, record, fieldName);
		}

	}

	public static List<Record> replaceDict(int headId, List<Record> list) {
		return replaceDict(DbMetaTool.getMetaData(headId), list);
	}

	public static List<Record> replaceDict(ZcurdMeta metaData, List<Record> list) {
		Iterator var3 = list.iterator();

		while(var3.hasNext()) {
			Record record = (Record)var3.next();
			replaceDict(metaData, record);
		}

		return list;
	}

	public static String getDbSource(String dbSource) {
		if (StringUtil.isEmpty(dbSource)) {
			dbSource = DbKit.getConfig().getName();
		}

		return dbSource;
	}
}
