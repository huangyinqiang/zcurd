//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zcurd.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswordUtil {
	public static final String defaultPassword = "123456";

	public PasswordUtil() {
	}

	private String md5(String inputStr) {
		BigInteger bigInteger = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] inputData = inputStr.getBytes();
			md.update(inputData);
			bigInteger = new BigInteger(md.digest());
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return bigInteger.toString(16);
	}

	public static String encodePassword(String password) {
		return (new PasswordUtil()).md5(password);
	}

	public static void main(String[] args) {
		System.out.println(encodePassword("123456"));
	}
}
