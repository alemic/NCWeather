package com.mlxy.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mlxy.ncweather.R;
import com.mlxy.xml.XmlDownloader;
import com.mlxy.xml.XmlFile;
import com.mlxy.xml.XmlParser;

public class MainActivity extends Activity {
	TextView weatherText;
	TextView temperatureText;
	Button updateButton;
	
	String weatherString = "";
	String temperatureString = "";
	String updateTime = "";
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
 		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			if (msg.what == 0x123) {
				MainActivity.this.weatherText.setText(weatherString);
				MainActivity.this.temperatureText.setText(temperatureString);
				
				Toast.makeText(MainActivity.this, updateTime, Toast.LENGTH_LONG).show();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		weatherText = (TextView) findViewById(R.id.text_weather);
		temperatureText = (TextView) findViewById(R.id.text_temperature);
		updateButton = (Button) findViewById(R.id.button1);
		
		updateButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				new Thread(new UpdateInfo()).start();
			}
		});
	}
	
	/** ��������һ���̣߳����������ȡxml�ļ�������*/
	class UpdateInfo implements Runnable {
		@Override
		public synchronized void run() {
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