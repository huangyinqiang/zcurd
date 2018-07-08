//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.service;

import com.zcurd.common.util.FreemarkUtil;
import com.zcurd.common.util.StringUtil;
import com.zcurd.common.util.UrlUtil;
import com.zcurd.model.Menu;
import com.zcurd.model.MenuBtn;
import com.zcurd.model.MenuDatarule;
import com.zcurd.model.SysUser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginService {
	private List<Menu> allMenuList;
	private List<Menu> userMenuList;

	public LoginService() {
	}

	public List<Menu> getUserMenu(SysUser user) {
		this.allMenuList = Menu.me.findAll();
		this.userMenuList = Menu.me.findByUser(user);
		Set<Menu> chainSet = new HashSet();
		Iterator var4 = this.userMenuList.iterator();

		while(var4.hasNext()) {
			Menu menu = (Menu)var4.next();
			chainSet.add(menu);
			this.getPChain(this.allMenuList, menu, chainSet);
		}

		List<Menu> chainList = new ArrayList(chainSet);
		Collections.sort(chainList, new Comparator<Menu>() {
			public int compare(Menu o1, Menu o2) {
				return o1.get("order_num") != null && o2.get("order_num") != null && o1.getInt("order_num") >= o2.getInt("order_num") ? 0 : -1;
			}
		});
		this.formatSubMenu(chainList);
		List<Menu> result = new ArrayList();
		Iterator var6 = chainList.iterator();

		while(var6.hasNext()) {
			Menu menu = (Menu)var6.next();
			result.add(menu);
		}

		return result;
	}

	public List<String> getNoAuthUrl() {
		List<String> urlList = new ArrayList();
		Iterator var3 = this.allMenuList.iterator();

		while(var3.hasNext()) {
			Menu menu1 = (Menu)var3.next();
			boolean flag = true;
			Iterator var6 = this.userMenuList.iterator();

			while(var6.hasNext()) {
				Menu menu2 = (Menu)var6.next();
				if (menu1.getInt("id") == menu2.getInt("id")) {
					flag = false;
					break;
				}
			}

			if (flag && StringUtil.isNotEmpty(menu1.getStr("menu_url"))) {
				urlList.add(menu1.getStr("menu_url"));
			}
		}

		return urlList;
	}

	public Map<String, Object> getNoAuthBtnUrl(SysUser user) {
		Map<Integer, Menu> userMenuMap = new HashMap();
		Iterator var4 = this.userMenuList.iterator();

		while(var4.hasNext()) {
			Menu menu = (Menu)var4.next();
			userMenuMap.put(menu.getInt("id"), menu);
		}

		Map<String, Object> result = new HashMap();
		List<String> btnUrlList = new ArrayList();
		List<MenuBtn> userBtnList = this.getDifference(MenuBtn.me.findAll(), MenuBtn.me.findByUser(user));
		Map<String, String> pageBtnMap = new HashMap();
		Iterator var8 = userBtnList.iterator();

		while(true) {
			MenuBtn menuBtn;
			String methodName;
			String menuUrl;
			do {
				do {
					Menu menu;
					do {
						if (!var8.hasNext()) {
							result.put("btnUrlList", btnUrlList);
							result.put("pageBtnMap", pageBtnMap);
							return result;
						}

						menuBtn = (MenuBtn)var8.next();
						methodName = menuBtn.getStr("method_name");
						menu = (Menu)userMenuMap.get(menuBtn.getInt("menu_id"));
					} while(menu == null);

					menuUrl = menu.getStr("menu_url");
					menuUrl = UrlUtil.removeUrlParam(menuUrl);
				} while(!StringUtil.isNotEmpty(methodName));
			} while(!StringUtil.isNotEmpty(menuUrl));

			String[] var15;
			int var14 = (var15 = methodName.split(",")).length;

			String btnName;
			for(int var13 = 0; var13 < var14; ++var13) {
				btnName = var15[var13];
				btnName = ("/" + btnName).replaceAll("//+", "/");
				if (btnName.lastIndexOf("/") > 0) {
					btnUrlList.add(btnName);
				} else {
					btnUrlList.add(UrlUtil.formatBaseUrl(menuUrl) + btnName);
				}
			}

			btnName = (String)pageBtnMap.get(menuUrl);
			if (StringUtil.isEmpty(btnName)) {
				btnName = menuBtn.getStr("class_name");
			} else {
				btnName = btnName + "," + menuBtn.getStr("class_name");
			}

			pageBtnMap.put(menuUrl, btnName);
		}
	}

	public Map<String, List<MenuDatarule>> getNoAuthDatarule(SysUser user) {
		Map<Integer, Menu> userMenuMap = new HashMap();
		Iterator var4 = this.userMenuList.iterator();

		while(var4.hasNext()) {
			Menu menu = (Menu)var4.next();
			userMenuMap.put(menu.getInt("id"), menu);
		}

		Map<String, List<MenuDatarule>> pageDataruleMap = new HashMap();
		List<MenuDatarule> userDataruleList = MenuDatarule.me.findByUser(user);
		Iterator var6 = userDataruleList.iterator();

		MenuDatarule menuDatarule;
		String menuUrl;
		while(var6.hasNext()) {
			menuDatarule = (MenuDatarule)var6.next();
			menuUrl = (String)menuDatarule.get("value");
			Map<String, Object> data = new HashMap();
			data.put("user", user);
			menuDatarule.set("value", FreemarkUtil.parse(menuUrl, data));
		}

		var6 = userDataruleList.iterator();

		while(var6.hasNext()) {
			menuDatarule = (MenuDatarule)var6.next();
			menuUrl = ((Menu)userMenuMap.get(menuDatarule.getInt("menu_id"))).getStr("menu_url");
			if (StringUtil.isNotEmpty(menuUrl)) {
				menuUrl = UrlUtil.formatBaseUrl(menuUrl);
				List<MenuDatarule> dataruleList = (List)pageDataruleMap.get(menuUrl);
				if (dataruleList == null) {
					dataruleList = new ArrayList();
				}

				((List)dataruleList).add(menuDatarule);
				pageDataruleMap.put(menuUrl, dataruleList);
			}
		}

		return pageDataruleMap;
	}

	public void getPChain(Collection<Menu> list, Menu menu, Set<Menu> chainlist) {
		Iterator var5 = list.iterator();

		while(var5.hasNext()) {
			Menu m = (Menu)var5.next();
			if (menu.getInt("parent_id") == m.getInt("id")) {
				chainlist.add(m);
				this.getPChain(list, m, chainlist);
			}
		}

	}

	private void formatSubMenu(Collection<Menu> list) {
		Iterator var3 = list.iterator();

		while(var3.hasNext()) {
			Menu menu1 = (Menu)var3.next();
			Iterator var5 = list.iterator();

			while(var5.hasNext()) {
				Menu menu2 = (Menu)var5.next();
				if (menu1.getInt("id") == menu2.getInt("parent_id")) {
					menu1.getSubMenuList().add(menu2);
				}
			}
		}

	}

	private List<MenuBtn> getDifference(List<MenuBtn> list1, List<MenuBtn> list2) {
		List<MenuBtn> result = new ArrayList();
		Iterator var5 = list1.iterator();

		while(var5.hasNext()) {
			MenuBtn menuBtn1 = (MenuBtn)var5.next();
			boolean flag = true;
			Iterator var8 = list2.iterator();

			while(var8.hasNext()) {
				MenuBtn menuBtn2 = (MenuBtn)var8.next();
				if (menuBtn1.getInt("id") == menuBtn2.getInt("id")) {
					flag = false;
					break;
				}
			}

			if (flag) {
				result.add(menuBtn1);
			}
		}

		return result;
	}
}
