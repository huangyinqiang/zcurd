//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.zcurd.common.util.UrlUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class AuthInterceptor implements Interceptor {
	public AuthInterceptor() {
	}

	public void intercept(Invocation inv) {
		Controller c = inv.getController();
		HttpServletRequest request = c.getRequest();
		int contextLength = request.getContextPath().length();
		String currUrl = request.getRequestURI().substring(contextLength);
		System.out.println(UrlUtil.formatBaseUrl(currUrl));
		List<String> noAuthUrl = (List)c.getSessionAttr("noAuthUrl");
		if (noAuthUrl != null) {
			Iterator var8 = noAuthUrl.iterator();

			while(var8.hasNext()) {
				String url = (String)var8.next();
				if (UrlUtil.formatBaseUrl(currUrl).equals(UrlUtil.formatBaseUrl(url))) {
					c.renderText("没有权限访问该页面！");
					return;
				}
			}

			Map<String, Object> authBtn = (Map)c.getSessionAttr("noAuthBtnUrl");
			List<String> noAuthBtnUrl = (List)authBtn.get("btnUrlList");
			Map<String, String> noAuthBtnMap = (Map)authBtn.get("pageBtnMap");
			request.setAttribute("noAuthBtn", noAuthBtnMap.get(currUrl));
			Iterator var11 = noAuthBtnUrl.iterator();

			while(var11.hasNext()) {
				String btnUrl = (String)var11.next();
				if (currUrl.equals(btnUrl)) {
					c.renderText("没有权限访问该页面！");
					return;
				}
			}
		}

		inv.invoke();
	}
}
