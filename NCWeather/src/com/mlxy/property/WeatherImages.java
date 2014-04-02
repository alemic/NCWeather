package com.mlxy.property;

import java.util.HashMap;
import java.util.Map;


public class WeatherImages {
	private static Map<String, String> map  = new HashMap<String, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8552791002402932770L;

		{
		put("qing", "00.png");
		put("duoyun", "01.png");
		put("yin", "02.png");
		put("zhenyu", "03.png");
		put("leizhenyu", "04.png");
		put("leizhenyubanyoubingbao", "05.png");
		put("yujiaxue", "06.png");
		put("xiaoyu", "07.png");
		put("zhongyu", "08.png");
		put("dayu", "09.png");
		put("baoyu", "10.png");
		put("dabaoyu", "11.png");
		put("tedabaoyu", "12.png");
		put("zhenxue", "13.png");
		put("xiaoxue", "14.png");
		put("zhongxue", "15.png");
		put("daxue", "16.png");
		put("baoxue", "17.png");
		put("wu", "18.png");
		put("dongyu", "19.png");
		put("shachenbao", "20.png");
		put("xiaodaozhongyu", "21.png");
		put("zhongdaodayu", "22.png");
		put("dadaobaoyu", "23.png");
		put("baoyudaodabaoyu", "24.png");
		put("dabaoyudaotedabaoyu", "25.png");
		put("xiaodaozhongxue", "26.png");
		put("zhongdaodaxue", "27.png");
		put("dadaobaoxue", "28.png");
		put("fuchen", "29.png");
		put("yangsha", "30.png");
		put("qiangshachenbao", "31.png");
		put("mai", "53.png");
		put("unknown", "undefined.png");
		}
	};
	
	/** 
	 * 根据天气名称的拼音返回对应图片的文件名。
	 * 
	 * @param weather 天气名的拼音。
	 * @return fileName 天气名称的拼音对应的文件名。如果找不到就返回unknown图片的文件名。*/
	public static String getFileName(String weather) {
		String fileName = map.get(weather);
		
		// 如果找不到就返回unknown图片的文件名。
		if (fileName == null) {
			fileName = map.get("unknown");
		}
		
		return fileName;
	}
}
