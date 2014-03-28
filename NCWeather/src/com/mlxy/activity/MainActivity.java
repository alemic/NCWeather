package com.mlxy.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.mlxy.ncweather.R;
import com.mlxy.xml.XmlDownloader;
import com.mlxy.xml.XmlFile;
import com.mlxy.xml.XmlParser;

public class MainActivity extends Activity {
	TextView weather;
	TextView temperature;
	String weatherString = "";
	String temperatureString = "";
	String updateTime = "";
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
 		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			if (msg.what == 0x123) {
				MainActivity.this.weather.setText(weatherString);
				MainActivity.this.temperature.setText(temperatureString);
				
				Toast.makeText(MainActivity.this, updateTime, Toast.LENGTH_LONG).show();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		weather = (TextView) findViewById(R.id.text_weather);
		temperature = (TextView) findViewById(R.id.text_temperature);
		
		new Thread(new XmlInitThread()).start();
	}
	
	/** ��������һ���̣߳����������ȡxml�ļ�������*/
	class XmlInitThread implements Runnable {
		@Override
		public void run() {
			// ����Xml������������Xml�ļ���
			new XmlDownloader.Builder(MainActivity.this)
					.setCity("�ϲ�")
					.setPassword("DJOYnieT8234jlsK")
					.setDay(0)
					.download();
			
			// ʵ�������������������ݡ�
			XmlParser parser = new XmlParser(MainActivity.this, XmlFile.getFile());
			weatherString = parser.getWeather();
			temperatureString = parser.getTemperature();
			
			try {
				updateTime = parser.getUpdateTime();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			handler.sendEmptyMessage(0x123);
		}
	}
}