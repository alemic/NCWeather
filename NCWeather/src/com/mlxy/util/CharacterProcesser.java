package com.mlxy.util;

import java.io.UnsupportedEncodingException;

public class CharacterProcesser {
	public static String encodeByGB2312(String s) {
		String result = null;
		try {
			result = java.net.URLEncoder.encode(s, "GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
