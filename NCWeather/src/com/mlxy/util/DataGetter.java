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
	
	/** ���캯������ʼ�������������֮���ȡ������Ҫ�����ݲ����档*/
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
	
	/** ��ȡ��������*/
	public String getCity() {
		// �򵥵ػ�ȡ�����������ء�
		String city = map.get(WeatherTag.CITY);
		
		return city;
	}
	
	/** ��ȡ��ʽ�����ȫ��������Ϣ��*/
	public String getWeather() {
		String weatherString;
		
		// ���ȫ��������һ����ֻ���ذ����������
		if (map.get(WeatherTag.DAY_WEATHER).equals(map.get(WeatherTag.NIGHT_WEATHER))) {
			weatherString = map.get(WeatherTag.DAY_WEATHER);
		} 
		// �����һ���ͷ��ء�[��������]ת[ҹ������]����
		else {
			weatherString = map.get(WeatherTag.DAY_WEATHER) + "ת" + map.get(WeatherTag.NIGHT_WEATHER);
		}
		
		return weatherString;
	}
	
	/** ��ȡ��ʽ�����ȫ���¶���Ϣ��*/
	public String getWholeDayTemperature() {
		// ���ء�[ҹ���¶�ֵ]��-[�����¶�ֵ]�桱��
		String temperatureString
		 		= map.get(WeatherTag.NIGHT_TEMPERATURE) + "��-" + map.get(WeatherTag.DAY_TEMPERATURE) + "��";
		
		return temperatureString;
	}
	
	/** ����ϵͳʱ�䷵�ذ����ҹ��ĵ�ǰ����¶ȡ�*/
	public String getCurrentTemperature() {
		// ��ȡ��ǰСʱ��24Сʱ�ơ�
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		
		String currentTemperatureString;
		if (hour <= 6 || hour >= 18) {
			currentTemperatureString = map.get(WeatherTag.NIGHT_SENDIBLE_TEMPERATURE) + "��";
		} else {
			currentTemperatureString = map.get(WeatherTag.DAY_SENDIBLE_TEMPERATURE) + "��";
		}
		
		return currentTemperatureString;
	}
	
	/** ���ظ�ʽ���������¶ȵȼ���ָ����������*/
	public String getSendibleTemperatureContent() {
		String result;
		String sendibleLevel = map.get(WeatherTag.SENDIBLE_LEVEL);
		String sendibleIndex = map.get(WeatherTag.SENDIBLE_INDEX);
		String sendibleDescr = map.get(WeatherTag.SENDIBLE_DESCRIPTION);
		
		result = "�ȼ���" + sendibleLevel + "\n"
				+ "ָ����" + sendibleIndex + "\n"
				+ "˵����" + sendibleDescr;
		
		return result;
	}
	
	/** ���ظ�ʽ�������Ⱦ��ȼ���ָ����������*/
	public String getPollutionContent() {
		String result;
		String pollutionLevel = map.get(WeatherTag.POLLUTION_LEVEL);
		String pollutionIndex = map.get(WeatherTag.POLLUTION_INDEX);
		String pollutionDescr = map.get(WeatherTag.POLLUTION_DESCRIPTION);
		
		result = "�ȼ���" + pollutionLevel + "\n"
				+ "ָ����" + pollutionIndex + "\n"
				+ "˵����" + pollutionDescr;
		
		return result;
	}
	
	/** ���ظ�ʽ����Ĵ��µȼ���ָ����������*/
	public String getDressingContent() {
		String result;
		String dressingLevel = map.get(WeatherTag.DRESSING_LEVEL);
		String dressingIndex = map.get(WeatherTag.DRESSING_INDEX);
		String dressingDescr = map.get(WeatherTag.DRESSING_DESCRIPTION);
		
		result = "�ȼ���" + dressingLevel + "\n"
				+ "ָ����" + dressingIndex + "\n"
				+ "˵����" + dressingDescr;
		
		return result;
	}
	
	/** ���ظ�ʽ����ĸ�ð�ȼ���ָ����������*/
	public String getColdContent() {
		String result;
		String coldLevel = map.get(WeatherTag.COLD_LEVEL);
		String coldIndex = map.get(WeatherTag.COLD_INDEX);
		String coldDescr = map.get(WeatherTag.COLD_DESCRIPTION);
		
		result = "�ȼ���" + coldLevel + "\n"
				+ "ָ����" + coldIndex + "\n"
				+ "˵����" + coldDescr;
		
		return result;
	}
	
	/** ���ظ�ʽ������˶��ȼ���ָ����������*/
	public String getExerciseDescrContent() {
		String result;
		String exerciseLevel = map.get(WeatherTag.EXERCISE_LEVEL);
		String exerciseIndex = map.get(WeatherTag.EXERCISE_INDEX);
		String exerciseDescr = map.get(WeatherTag.EXERCISE_DESCRIPTION);
		
		result = "�ȼ���" + exerciseLevel + "\n"
				+ "ָ����" + exerciseIndex + "\n"
				+ "˵����" + exerciseDescr;
		
		return result;
	}
	
	/** ��ȡ����ʱ���ַ��������ء�*/
	public String getUpdateTime() {
		String timeString = parser.getUpdateTime();
		
		return timeString;
	}
	
	/** ��ȡ����ϵͳʱ�䷵�ذ����ҹ�������ͼƬ��*/
	public Bitmap getWeatherImage() {
		// ��ȡ��ǰСʱ��24Сʱ�ơ�
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		
		// ����ʱ���ȡ�ļ������ơ�����ʱ���ȡ��������������ȡ��ӦͼƬ���ļ�����
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
		
		// ��ȡ��Դ����
		AssetManager manager = this.parent.getResources().getAssets();
		
		// ���Ѿ��õ��ı�����ȡ��ӦͼƬ����
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
