//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.common.handler;

import com.jfinal.handler.Handler;
import com.zcurd.common.util.UrlUtil;
import com.zcurd.model.MenuDatarule;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ZcurdHandler extends Handler {
	public ZcurdHandler() {
	}

	public void handle(String target, HttpServletRequest request, HttpServletResponse response, boolean[] isHandled) {
		HttpSession session = request.getSession();
		if (!target.startsWith("/res/") && session.getAttribute("noAuthDatarule") != null) {
			Map<String, List<MenuDatarule>> noAuthDatarule = (Map)request.getSession().getAttribute("noAuthDatarule");
			List<MenuDatarule> menuDataruleList = (List)noAuthDatarule.get(UrlUtil.formatBaseUrl(target));
			if (menuDataruleList != null) {
				request.setAttribute("menuDataruleList", menuDataruleList);
				String authField = "";

				MenuDatarule menuDatarule;
				for(Iterator var10 = menuDataruleList.iterator(); var10.hasNext(); authField = authField + "," + menuDatarule.getFieldName()) {
					menuDatarule = (MenuDatarule)var10.next();
				}

				request.setAttribute("authField", authField.replaceAll("^,", ""));
			}
		}

		String pre = "/zcurd/";
		if (target.startsWith(pre)) {
			String[] params = target.split("/");
			if (params.length > 3) {
				target = pre + params[3];
				request.setAttribute("headId", params[2]);

				for(int i = 4; i < params.length; ++i) {
					target = target + params[i];
				}
			}
		}

		this.next.handle(target, request, response, isHandled);
	}
}
