package com.mlxy.activity;

import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.widget.TextView;
import android.widget.Toast;

import com.mlxy.ncweather.R;
import com.mlxy.property.WeatherTag;
import com.mlxy.xml.XmlDownloader;
import com.mlxy.xml.XmlParser;

public class MainActivity extends Activity {
	TextView cityText;
	TextView currentTemperatureText;
	TextView weatherText;
	TextView temperatureText;
	
	String cityString = "";
	String currentTemperatureString = "";
	String weatherString = "";
	String temperatureString = "";
	String updateTimeString = "";
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
 		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			if (msg.what == 0x123) {
				MainActivity.this.cityText.setText(cityString);
				MainActivity.this.weatherText.setText(weatherString);
				MainActivity.this.currentTemperatureText.setText(currentTemperatureString);
				MainActivity.this.temperatureText.setText(temperatureString);
				
				Toast.makeText(MainActivity.this, updateTimeString, Toast.LENGTH_LONG).show();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		cityText = (TextView) findViewById(R.id.textCity);
		currentTemperatureText = (TextView) findViewById(R.id.textCurrentTemperature);
		weatherText = (TextView) findViewById(R.id.textWeather);
		temperatureText = (TextView) findViewById(R.id.textTemperature);
		
		DisplayMetrics dm = this.getResources().getDisplayMetrics();
		int textSize;
		textSize = (int) (20 * dm.density);
		cityText.setTextSize(textSize);
		textSize = (int) (30 * dm.density);
		currentTemperatureText.setTextSize(textSize);
		textSize = (int) (15 * dm.density);
		weatherText.setTextSize(textSize);
		textSize = (int) (10 * dm.density);
		temperatureText.setTextSize(textSize);
		
		Thread download = new Thread(new DownloadXml());
		try {
			download.start();
			download.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
		
		new Thread(new UpdateInfo()).start();
	}
	
	/** 下载XML文件的线程。*/
	class DownloadXml implements Runnable {
		@Override
		public void run() {
			// 构建Xml下载器并下载Xml文件。
			new XmlDownloader.Builder(MainActivity.this)
					.setCity("南昌")
					.setPassword("DJOYnieT8234jlsK")
					.setDay(0)
					.download();
		}
	}
	
	/** 解析XML文件的线程。*/
	class UpdateInfo implements Runnable {
		@Override
		public void run() {
			// 实例化解析器并解析数据。
			XmlParser parser = new XmlParser(MainActivity.this);
			Map<String, String> map = null;
			try {
				map = parser.getContentsByTags(WeatherTag.DAY_WEATHER, 
										 	   WeatherTag.NIGHT_WEATHER, 
										 	   WeatherTag.DAY_TEMPERATURE,
										 	   WeatherTag.NIGHT_TEMPERATURE,
										 	   WeatherTag.CITY,
										 	   WeatherTag.DAY_SENDIBLE_TEMPERATURE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 获取各个字符串。
			cityString = map.get(WeatherTag.CITY);
			currentTemperatureString = map.get(WeatherTag.DAY_SENDIBLE_TEMPERATURE) + "℃";
			temperatureString = map.get(WeatherTag.NIGHT_TEMPERATURE) + "-" + map.get(WeatherTag.DAY_TEMPERATURE) + "℃";
			if (map.get(WeatherTag.DAY_WEATHER).equals(map.get(WeatherTag.NIGHT_WEATHER))) {
				weatherString = map.get(WeatherTag.DAY_WEATHER);
			} else {
				weatherString = map.get(WeatherTag.DAY_WEATHER) + "转" + map.get(WeatherTag.NIGHT_WEATHER);
			}
			updateTimeString = parser.getUpdateTime();
			
			handler.sendEmptyMessage(0x123);
		}
	}
}