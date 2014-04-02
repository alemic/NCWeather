package com.mlxy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Map;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mlxy.property.WeatherImages;
import com.mlxy.property.WeatherTag;
import com.mlxy.xml.XmlParser;

public class DataGetter {
	private Context parent;
	private XmlParser parser;
	private Map<String, String> map;
	
	/** 构造函数，初始化两个类变量，之后获取所有需要的数据并储存。*/
	public DataGetter(Context parent) {
		this.parent = parent;
		this.parser = new XmlParser(parent);
		this.map = null;
				
		try {
			map = parser.getContentsByTags(WeatherTag.DAY_WEATHER, 
									 	   WeatherTag.NIGHT_WEATHER, 
									 	   WeatherTag.DAY_WEATHER_PINYIN,
									 	   WeatherTag.NIGHT_WEATHER_PINYIN,
									 	   
									 	   WeatherTag.DAY_TEMPERATURE,
									 	   WeatherTag.NIGHT_TEMPERATURE,
									 	   WeatherTag.CITY,
									 	   WeatherTag.DAY_SENDIBLE_TEMPERATURE,
									 	   WeatherTag.NIGHT_SENDIBLE_TEMPERATURE,
									 	   
									 	   WeatherTag.SENDIBLE_LEVEL,
									 	   WeatherTag.SENDIBLE_INDEX,
									 	   WeatherTag.SENDIBLE_DESCRIPTION,
									 	   
									 	   WeatherTag.POLLUTION_LEVEL,
									 	   WeatherTag.POLLUTION_INDEX,
									 	   WeatherTag.POLLUTION_DESCRIPTION,
									 	   
									 	   WeatherTag.DRESSING_LEVEL,
									 	   WeatherTag.DRESSING_INDEX,
									 	   WeatherTag.DRESSING_DESCRIPTION,
									 	   
									 	   WeatherTag.COLD_LEVEL,
									 	   WeatherTag.COLD_INDEX,
									 	   WeatherTag.COLD_DESCRIPTION,
									 	   
									 	   WeatherTag.EXERCISE_LEVEL,
									 	   WeatherTag.EXERCISE_INDEX,
									 	   WeatherTag.EXERCISE_DESCRIPTION);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 获取城市名。*/
	public String getCity() {
		// 简单地获取城市名并返回。
		String city = map.get(WeatherTag.CITY);
		
		return city;
	}
	
	/** 获取格式化后的全天天气信息。*/
	public String getWeather() {
		String weatherString;
		
		// 如果全天天气都一样就只返回白天的天气。
		if (map.get(WeatherTag.DAY_WEATHER).equals(map.get(WeatherTag.NIGHT_WEATHER))) {
			weatherString = map.get(WeatherTag.DAY_WEATHER);
		} 
		// 如果不一样就返回“[白天天气]转[夜晚天气]”。
		else {
			weatherString = map.get(WeatherTag.DAY_WEATHER) + "转" + map.get(WeatherTag.NIGHT_WEATHER);
		}
		
		return weatherString;
	}
	
	/** 获取格式化后的全天温度信息。*/
	public String getWholeDayTemperature() {
		// 返回“[夜晚温度值]℃-[白天温度值]℃”。
		String temperatureString
		 		= map.get(WeatherTag.NIGHT_TEMPERATURE) + "℃-" + map.get(WeatherTag.DAY_TEMPERATURE) + "℃";
		
		return temperatureString;
	}
	
	/** 根据系统时间返回白天或夜晚的当前体感温度。*/
	public String getCurrentTemperature() {
		// 获取当前小时，24小时制。
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		
		String currentTemperatureString;
		if (hour <= 6 || hour >= 18) {
			currentTemperatureString = map.get(WeatherTag.NIGHT_SENDIBLE_TEMPERATURE) + "℃";
		} else {
			currentTemperatureString = map.get(WeatherTag.DAY_SENDIBLE_TEMPERATURE) + "℃";
		}
		
		return currentTemperatureString;
	}
	
	/** 返回格式化后的体感温度等级，指数和描述。*/
	public String getSendibleTemperatureContent() {
		String result;
		String sendibleLevel = map.get(WeatherTag.SENDIBLE_LEVEL);
		String sendibleIndex = map.get(WeatherTag.SENDIBLE_INDEX);
		String sendibleDescr = map.get(WeatherTag.SENDIBLE_DESCRIPTION);
		
		result = "等级：" + sendibleLevel + "\n"
				+ "指数：" + sendibleIndex + "\n"
				+ "说明：" + sendibleDescr;
		
		return result;
	}
	
	/** 返回格式化后的污染物等级，指数和描述。*/
	public String getPollutionContent() {
		String result;
		String pollutionLevel = map.get(WeatherTag.POLLUTION_LEVEL);
		String pollutionIndex = map.get(WeatherTag.POLLUTION_INDEX);
		String pollutionDescr = map.get(WeatherTag.POLLUTION_DESCRIPTION);
		
		result = "等级：" + pollutionLevel + "\n"
				+ "指数：" + pollutionIndex + "\n"
				+ "说明：" + pollutionDescr;
		
		return result;
	}
	
	/** 返回格式化后的穿衣等级，指数和描述。*/
	public String getDressingContent() {
		String result;
		String dressingLevel = map.get(WeatherTag.DRESSING_LEVEL);
		String dressingIndex = map.get(WeatherTag.DRESSING_INDEX);
		String dressingDescr = map.get(WeatherTag.DRESSING_DESCRIPTION);
		
		result = "等级：" + dressingLevel + "\n"
				+ "指数：" + dressingIndex + "\n"
				+ "说明：" + dressingDescr;
		
		return result;
	}
	
	/** 返回格式化后的感冒等级，指数和描述。*/
	public String getColdContent() {
		String result;
		String coldLevel = map.get(WeatherTag.COLD_LEVEL);
		String coldIndex = map.get(WeatherTag.COLD_INDEX);
		String coldDescr = map.get(WeatherTag.COLD_DESCRIPTION);
		
		result = "等级：" + coldLevel + "\n"
				+ "指数：" + coldIndex + "\n"
				+ "说明：" + coldDescr;
		
		return result;
	}
	
	/** 返回格式化后的运动等级，指数和描述。*/
	public String getExerciseDescrContent() {
		String result;
		String exerciseLevel = map.get(WeatherTag.EXERCISE_LEVEL);
		String exerciseIndex = map.get(WeatherTag.EXERCISE_INDEX);
		String exerciseDescr = map.get(WeatherTag.EXERCISE_DESCRIPTION);
		
		result = "等级：" + exerciseLevel + "\n"
				+ "指数：" + exerciseIndex + "\n"
				+ "说明：" + exerciseDescr;
		
		return result;
	}
	
	/** 获取更新时间字符串并返回。*/
	public String getUpdateTime() {
		String timeString = parser.getUpdateTime();
		
		return timeString;
	}
	
	/** 获取根据系统时间返回白天或夜晚的天气图片。*/
	public Bitmap getWeatherImage() {
		// 获取当前小时，24小时制。
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		
		// 根据时间获取文件夹名称。根据时间获取天气，用天气获取对应图片的文件名。
		String dayOrNight = null;
		String fileName = null;
		
		if (hour <= 6 || hour >= 18) {
			dayOrNight = "night";
			String weatherPinyin = map.get(WeatherTag.NIGHT_WEATHER_PINYIN);
			fileName = WeatherImages.getFileName(weatherPinyin);
		} else {
			dayOrNight = "day";
			String weatherPinyin = map.get(WeatherTag.DAY_WEATHER_PINYIN);
			fileName = WeatherImages.getFileName(weatherPinyin);
		}
		
		// 获取资源包。
		AssetManager manager = this.parent.getResources().getAssets();
		
		// 用已经得到的变量获取对应图片对象。
		Bitmap image = null;
		
		try {
			InputStream in = manager.open("weather/" + dayOrNight+ "/" + fileName);
			image = BitmapFactory.decodeStream(in);
			in.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
}
