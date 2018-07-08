//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.filter;

import com.zcurd.common.util.UrlUtil;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	public LoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		HttpSession session = request.getSession();
		String currUrl = UrlUtil.formatUrl(request.getRequestURI());
		String contextPath = request.getContextPath();
		if (!currUrl.endsWith("image") && !currUrl.endsWith("login") && !currUrl.startsWith(contextPath + "/res")) {
			if (session.getAttribute("sysUser") == null) {
				response.sendRedirect(request.getContextPath() + "/login");
				return;
			}

			if (currUrl.equals(contextPath) || currUrl.equals(contextPath + "/index")) {
				response.sendRedirect(request.getContextPath() + "/main");
				return;
			}
		}

		chain.doFilter(req, res);
	}

	public void init(FilterConfig chain) throws ServletException {
	}
}
