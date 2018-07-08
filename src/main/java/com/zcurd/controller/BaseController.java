//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.zcurd.common.util.Pager;
import com.zcurd.model.MenuDatarule;
import com.zcurd.model.SysOplog;
import com.zcurd.model.SysUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BaseController extends Controller {
	public BaseController() {
	}

	protected void renderDatagrid(Page<?> pageData) {
		Map<String, Object> datagrid = new HashMap();
		datagrid.put("rows", pageData.getList());
		datagrid.put("total", pageData.getTotalRow());
		this.renderJson(datagrid);
	}

	protected void renderDatagrid(List<?> list, int total) {
		this.renderDatagrid(list, total, (List)null);
	}

	protected void renderDatagrid(List<?> list, int total, List<Map<String, Object>> footer) {
		Map<String, Object> datagrid = new HashMap();
		datagrid.put("rows", list);
		datagrid.put("total", total);
		if (footer != null && footer.size() > 0) {
			datagrid.put("footer", footer);
		}

		this.renderJson(datagrid);
	}

	protected void renderDatagrid(List<Record> list) {
		Map<String, Object> datagrid = new HashMap();
		datagrid.put("rows", list);
		this.renderJson(datagrid);
	}

	protected void renderSuccess(String msg) {
		Map<String, Object> result = new HashMap();
		result.put("result", "success");
		result.put("msg", msg);
		this.renderJson(result);
	}

	protected void renderSuccess() {
		Map<String, Object> result = new HashMap();
		result.put("result", "success");
		this.renderJson(result);
	}

	protected void renderFailed(String msg) {
		Map<String, Object> result = new HashMap();
		result.put("result", "fail");
		result.put("msg", msg);
		this.renderJson(result);
	}

	protected void renderFailed() {
		Map<String, Object> result = new HashMap();
		result.put("result", "fail");
		this.renderJson(result);
	}

	protected SysUser getSessionUser() {
		return (SysUser)this.getSessionAttr("sysUser");
	}

	protected Pager getPager() {
		Pager pager = new Pager();
		pager.setPage(this.getParaToInt("page", 0));
		pager.setRows(this.getParaToInt("rows", 0));
		return pager;
	}

	protected Object[] getQueryParams() {
		long gid = (Long)this.getSessionUser().get("id");
		List<String> properties = new ArrayList();
		List<String> symbols = new ArrayList();
		List<Object> values = new ArrayList();
		Map<String, String[]> paraMap = this.getParaMap();
		String urlPath = this.getRequest().getServletPath();
		List list;
		StringBuffer sb;
		int i;
		SysUser user;
		String tmpStr;
		if (urlPath.indexOf("customer") > -1 || urlPath.indexOf("customer2") > -1) {
			properties.add("status");
			symbols.add("=");
			values.add("0");
			if (gid != 1L) {
				list = SysUser.me.findByPid(gid);
				if (list != null && list.size() >= 1) {
					sb = new StringBuffer();
					sb.append(gid + ",");

					for(i = 0; i < list.size(); ++i) {
						user = (SysUser)list.get(i);
						sb.append(user.get("id") + ",");
					}

					tmpStr = sb.substring(0, sb.length() - 1);
					properties.add("gid");
					symbols.add("in");
					values.add("(" + tmpStr + ")");
				} else {
					properties.add("gid");
					symbols.add("=");
					values.add("" + gid);
				}
			} else {
				properties.add("gid");
				symbols.add("=");
				values.add("" + gid);
			}
		}

		if (urlPath.indexOf("charge") > -1 || urlPath.indexOf("lostcard") > -1 || urlPath.indexOf("device") > -1 || urlPath.indexOf("config") > -1 || urlPath.indexOf("config2") > -1 || urlPath.indexOf("weixin") > -1 || urlPath.indexOf("cardlog") > -1 || urlPath.indexOf("money") > -1) {
			if (gid != 1L) {
				list = SysUser.me.findByPid(gid);
				if (list != null && list.size() >= 1) {
					sb = new StringBuffer();
					sb.append(gid + ",");

					for(i = 0; i < list.size(); ++i) {
						user = (SysUser)list.get(i);
						sb.append(user.get("id") + ",");
					}

					tmpStr = sb.substring(0, sb.length() - 1);
					properties.add("gid");
					symbols.add("in");
					values.add("(" + tmpStr + ")");
				} else {
					properties.add("gid");
					symbols.add("=");
					values.add("" + gid);
				}
			} else {
				properties.add("gid");
				symbols.add("=");
				values.add("" + gid);
			}
		}

		Iterator var15 = paraMap.keySet().iterator();

		while(true) {
			String paraName;
			do {
				if (!var15.hasNext()) {
					if (this.getAttr("menuDataruleList") != null) {
						list = (List)this.getAttr("menuDataruleList");
						Iterator var19 = list.iterator();

						while(var19.hasNext()) {
							MenuDatarule menuDatarule = (MenuDatarule)var19.next();
							properties.add(menuDatarule.getFieldName());
							symbols.add(menuDatarule.getSymbol());
							values.add(menuDatarule.getValue());
						}
					}

					return new Object[]{properties.toArray(new String[0]), symbols.toArray(new String[0]), values.toArray(new Object[0])};
				}

				paraName = (String)var15.next();
				tmpStr = "queryParams[";
			} while(!paraName.startsWith(tmpStr));

			String field = paraName.substring(tmpStr.length(), paraName.length() - 1);
			String symbol = "=";
			String value = ((String[])paraMap.get(paraName))[0];
			if (field.startsWith("_start_")) {
				field = field.replaceAll("^_start_", "");
				symbol = ">=";
			} else if (field.startsWith("_end_")) {
				field = field.replaceAll("^_end_", "");
				symbol = "<=";
			}

			if (value.startsWith("*") && value.endsWith("*")) {
				value = "%" + value.substring(1, value.length() - 1) + "%";
				symbol = "like";
			} else if (value.startsWith("*")) {
				value = "%" + value.substring(1);
				symbol = "like";
			} else if (value.endsWith("*")) {
				value = value.substring(0, value.length() - 1) + "%";
				symbol = "like";
			}

			properties.add(field);
			symbols.add(symbol);
			values.add(value);
		}
	}

	protected Object[] getQueryParams1() {
		List<String> properties = new ArrayList();
		List<String> symbols = new ArrayList();
		List<Object> values = new ArrayList();
		Map<String, String[]> paraMap = this.getParaMap();
		Iterator var6 = paraMap.keySet().iterator();

		while(true) {
			String paraName;
			String prefix;
			do {
				if (!var6.hasNext()) {
					if (this.getAttr("menuDataruleList") != null) {
						List<MenuDatarule> menuDataruleList = (List)this.getAttr("menuDataruleList");
						Iterator var13 = menuDataruleList.iterator();

						while(var13.hasNext()) {
							MenuDatarule menuDatarule = (MenuDatarule)var13.next();
							properties.add(menuDatarule.getFieldName());
							symbols.add(menuDatarule.getSymbol());
							values.add(menuDatarule.getValue());
						}
					}

					return new Object[]{properties.toArray(new String[0]), symbols.toArray(new String[0]), values.toArray(new Object[0])};
				}

				paraName = (String)var6.next();
				prefix = "queryParams[";
			} while(!paraName.startsWith(prefix));

			String field = paraName.substring(prefix.length(), paraName.length() - 1);
			String symbol = "=";
			String value = ((String[])paraMap.get(paraName))[0];
			if (field.startsWith("_start_")) {
				field = field.replaceAll("^_start_", "");
				symbol = ">=";
			} else if (field.startsWith("_end_")) {
				field = field.replaceAll("^_end_", "");
				symbol = "<=";
			}

			if (value.startsWith("*") && value.endsWith("*")) {
				value = "%" + value.substring(1, value.length() - 1) + "%";
				symbol = "like";
			} else if (value.startsWith("*")) {
				value = "%" + value.substring(1);
				symbol = "like";
			} else if (value.endsWith("*")) {
				value = value.substring(0, value.length() - 1) + "%";
				symbol = "like";
			}

			properties.add(field);
			symbols.add(symbol);
			values.add(value);
		}
	}

	protected String getOrderBy() {
		String sqlOrderBy = "";
		Map<String, String[]> paraMap = this.getParaMap();
		if (paraMap.get("sort") != null && ((String[])paraMap.get("sort")).length > 0) {
			String[] sort = ((String[])paraMap.get("sort"))[0].split(",");
			String[] order = ((String[])paraMap.get("order"))[0].split(",");
			sqlOrderBy = sort[0] + " " + order[0];

			for(int i = 1; i < sort.length; ++i) {
				sqlOrderBy = sqlOrderBy + ", " + sort[i] + " " + order[i];
			}
		}

		return sqlOrderBy;
	}

	protected void addOpLog(String opContent) {
		((SysOplog)((SysOplog)((SysOplog)((SysOplog)((SysOplog)SysOplog.me.remove("id")).set("user_id", this.getSessionUser().get("id"))).set("op_content", opContent)).set("ip", this.getRemoteAddress())).set("create_time", new Date())).save();
	}

	protected String getRemoteAddress() {
		String ip = this.getRequest().getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = this.getRequest().getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = this.getRequest().getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = this.getRequest().getRemoteAddr();
		}

		return ip;
	}
}
