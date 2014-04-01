package com.mlxy.activity;

import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.mlxy.ncweather.R;
import com.mlxy.property.WeatherTag;
import com.mlxy.xml.XmlDownloader;
import com.mlxy.xml.XmlParser;

public class MainActivity extends Activity implements OnClickListener {
	FragmentManager manager;
	FragmentTransaction trans;
	
	TextView cityText;
	TextView currentTemperatureText;
	TextView weatherText;
	TextView temperatureText;
	
	TextView fragment1;
	TextView fragment2;
	TextView fragment3;
	TextView fragment4;
	TextView fragment5;
	
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
				
				trans = manager.beginTransaction();
				trans.replace(R.id.contentLayout, new Fragment1());
				trans.commit();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// 获取上半屏组件。
		cityText = (TextView) findViewById(R.id.textCity);
		currentTemperatureText = (TextView) findViewById(R.id.textCurrentTemperature);
		weatherText = (TextView) findViewById(R.id.textWeather);
		temperatureText = (TextView) findViewById(R.id.textTemperature);
		
		// 设置上半屏UI。
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
		
		// 获取下半屏组件。
		fragment1 = (TextView) findViewById(R.id.textView1);
		fragment2 = (TextView) findViewById(R.id.textView2);
		fragment3 = (TextView) findViewById(R.id.textView3);
		fragment4 = (TextView) findViewById(R.id.textView4);
		fragment5 = (TextView) findViewById(R.id.textView5);
		
		manager = this.getFragmentManager();
		
		// 设置下半屏UI。
		textSize = (int) (12 * dm.density);
		fragment1.setTextSize(textSize);
		fragment2.setTextSize(textSize);
		fragment3.setTextSize(textSize);
		fragment4.setTextSize(textSize);
		fragment5.setTextSize(textSize);
		
		fragment1.setOnClickListener(this);
		fragment2.setOnClickListener(this);
		fragment3.setOnClickListener(this);
		fragment4.setOnClickListener(this);
		fragment5.setOnClickListener(this);
		
		// 下载与更新内容。
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

	@Override
	public void onClick(View v) {
		trans = manager.beginTransaction();
		
		switch (v.getId()) {
		case R.id.textView1:
			trans.replace(R.id.contentLayout, new Fragment1());
			break;
		case R.id.textView2:
			trans.replace(R.id.contentLayout, new Fragment2());
			break;
		case R.id.textView3:
			trans.replace(R.id.contentLayout, new Fragment3());
			break;
		case R.id.textView4:
			trans.replace(R.id.contentLayout, new Fragment4());
			break;
		case R.id.textView5:
			trans.replace(R.id.contentLayout, new Fragment5());
			break;
		}
		
		trans.commit();
	}
}