//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.zcurd.common.ErrorMsgException;
import java.util.HashMap;
import java.util.Map;

public class ErrorInterceptor implements Interceptor {
	public ErrorInterceptor() {
	}

	public void intercept(Invocation inv) {
		Controller c = inv.getController();

		try {
			inv.invoke();
		} catch (ErrorMsgException var5) {
			Map<String, Object> result = new HashMap();
			result.put("result", "fail");
			result.put("msg", var5.getMessage());
			c.renderJson(result);
		}

	}
}
