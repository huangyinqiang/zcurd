//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.common.handler;

import com.zcurd.vo.ZcurdMeta;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface CurdHandle {
	void add(ZcurdMeta var1, HttpServletRequest var2, Map<String, String[]> var3);

	void update(ZcurdMeta var1, HttpServletRequest var2, Map<String, String[]> var3);

	void delete(ZcurdMeta var1, HttpServletRequest var2, Map<String, String[]> var3);
}
