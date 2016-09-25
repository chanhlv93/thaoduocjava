package com.minitwit.util;

import java.util.Random;

public class Utils {
	public static String AddNameImages(String name) {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String strRandom = sb.toString();
		String ouputStr = name.substring(0, name.length() - 4) + "-" + strRandom + name.substring(name.length() - 4, name.length());
		return ouputStr;
	}
}
