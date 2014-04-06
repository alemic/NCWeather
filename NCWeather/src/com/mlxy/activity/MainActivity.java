package com.mlxy.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mlxy.ncweather.R;
import com.mlxy.util.DataGetter;
import com.mlxy.xml.XmlDownloader;

/** 
 * �����棬Ӧ��ĿǰΨһ��Activity��
 * 
 * @author mlxy
 * */
public class MainActivity extends Activity implements OnClickListener {
	// ����Fragment�������
	FragmentManager manager;
	FragmentTransaction trans;
	
	// �ϰ������ݵ����ݡ�
	String cityString = "";
	String currentTemperatureString = "";
	String weatherString = "";
	String temperatureString = "";
	String updateTimeString = "";
	Bitmap weatherImage;
	
	// �ϰ������ݵĿؼ���
	TextView cityText;
	TextView currentTemperatureText;
	TextView weatherText;
	TextView temperatureText;
	ImageView weatherImageView;
	
	// �°�����ѡ���
	TextView fragmentText1;
	TextView fragmentText2;
	TextView fragmentText3;
	TextView fragmentText4;
	TextView fragmentText5;
	
	// �°����ľ���˵�����ݡ�
	String content1;
	String content2;
	String content3;
	String content4;
	String content5;
	
	// �°����ļ���Fragment��
	Fragment fragment1;
	Fragment fragment2;
	Fragment fragment3;
	Fragment fragment4;
	Fragment fragment5;
	
	/** �����������ڽ���Ϣ���µ�UI�С�*/
	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			if (msg.what == 0x123) {
				// �����ϰ�����������Ϣ��
				cityText.setText(cityString);
				weatherText.setText(weatherString);
				weatherText.setHorizontallyScrolling(true);
				currentTemperatureText.setText(currentTemperatureString);
				temperatureText.setText(temperatureString);
				
				weatherImageView.setImageBitmap(weatherImage);
				
				// ���°����ļ���Fragment�������ݡ�
				Bundle bundle1 = new Bundle();
				bundle1.putString("mlxy", content1);
				fragment1.setArguments(bundle1);
				
				Bundle bundle2 = new Bundle();
				bundle2.putString("mlxy", content2);
				fragment2.setArguments(bundle2);
				
				Bundle bundle3 = new Bundle();
				bundle3.putString("mlxy", content3);
				fragment3.setArguments(bundle3);
				
				Bundle bundle4 = new Bundle();
				bundle4.putString("mlxy", content4);
				fragment4.setArguments(bundle4);
				
				Bundle bundle5 = new Bundle();
				bundle5.putString("mlxy", content5);
				fragment5.setArguments(bundle5);
				
				// ��ʾ��һ��Fragment��
				trans = manager.beginTransaction();
				trans.replace(R.id.contentLayout, fragment1);
				trans.commit();
				
				// ������ʾ����ʾ����ʱ�䡣
				Toast toast = Toast.makeText(MainActivity.this, updateTimeString, Toast.LENGTH_LONG);
				toast.setGravity(Gravity.BOTTOM, 0, 0);
				toast.show();
				
				return true;
			}
			
			return false;
		}
	});

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// ��ȡ�ϰ����������
		cityText = (TextView) findViewById(R.id.textCity);
		currentTemperatureText = (TextView) findViewById(R.id.textCurrentTemperature);
		weatherText = (TextView) findViewById(R.id.textWeather);
		temperatureText = (TextView) findViewById(R.id.textTemperature);
		
		weatherImageView = (ImageView) findViewById(R.id.imageView1);
		
		// ��ȡӲ�����������Լ��㲢�����ϰ���UI���塣
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
		
		// ��ȡ�°����������
		fragmentText1 = (TextView) findViewById(R.id.textView1);
		fragmentText2 = (TextView) findViewById(R.id.textView2);
		fragmentText3 = (TextView) findViewById(R.id.textView3);
		fragmentText4 = (TextView) findViewById(R.id.textView4);
		fragmentText5 = (TextView) findViewById(R.id.textView5);
		
		fragment1 = new Fragment1();
		fragment2 = new Fragment2();
		fragment3 = new Fragment3();
		fragment4 = new Fragment4();
		fragment5 = new Fragment5();
		
		manager = this.getFragmentManager();
		
		// ����ѡ����壬���ؼ�������
		textSize = (int) (12 * dm.density);
		fragmentText1.setTextSize(textSize);
		fragmentText2.setTextSize(textSize);
		fragmentText3.setTextSize(textSize);
		fragmentText4.setTextSize(textSize);
		fragmentText5.setTextSize(textSize);
		
		fragmentText1.setOnClickListener(this);
		fragmentText2.setOnClickListener(this);
		fragmentText3.setOnClickListener(this);
		fragmentText4.setOnClickListener(this);
		fragmentText5.setOnClickListener(this);
		
		// ���������̣߳����غ�������ݡ�
		Thread download = new Thread(new DownloadXml());
		try {
			download.start();
			download.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
		
		this.updateInfo();
	}
	
	/** ����XML�ļ����̡߳�*/
	class DownloadXml implements Runnable {
		@Override
		public void run() {
			// ����Xml������������Xml�ļ���
			new XmlDownloader.Builder(MainActivity.this)
					.setCity("�ϲ�")
					.setPassword("DJOYnieT8234jlsK")
					.setDay(0)
					.download();
		}
	}
	
	/** ��ȡUI����������ݲ�����Ӧ�ı�����ֵ��*/
	private void updateInfo() {
		DataGetter getter = new DataGetter(MainActivity.this);
		
		cityString = getter.getCity();
		currentTemperatureString = getter.getCurrentTemperature();
		weatherString = getter.getWeather();
		temperatureString = getter.getWholeDayTemperature();
		updateTimeString = getter.getUpdateTime();
		
		weatherImage = getter.getWeatherImage();
		
		content1 = getter.getSendibleTemperatureContent();
		content2 = getter.getPollutionContent();
		content3 = getter.getDressingContent();
		content4 = getter.getColdContent();
		content5 = getter.getExerciseDescrContent();
		
		handler.sendEmptyMessage(0x123);
	}

	@Override
	public void onClick(View v) {
		trans = manager.beginTransaction();
		
		switch (v.getId()) {
		case R.id.textView1:
			trans.replace(R.id.contentLayout, fragment1);
			colorSetSelected(fragmentText1);
			colorSetUnselected(fragmentText2);
			colorSetUnselected(fragmentText3);
			colorSetUnselected(fragmentText4);
			colorSetUnselected(fragmentText5);
			break;
		case R.id.textView2:
			trans.replace(R.id.contentLayout, fragment2);
			colorSetSelected(fragmentText2);
			colorSetUnselected(fragmentText1);
			colorSetUnselected(fragmentText3);
			colorSetUnselected(fragmentText4);
			colorSetUnselected(fragmentText5);
			break;
		case R.id.textView3:
			trans.replace(R.id.contentLayout, fragment3);
			colorSetSelected(fragmentText3);
			colorSetUnselected(fragmentText2);
			colorSetUnselected(fragmentText1);
			colorSetUnselected(fragmentText4);
			colorSetUnselected(fragmentText5);
			break;
		case R.id.textView4:
			trans.replace(R.id.contentLayout, fragment4);
			colorSetSelected(fragmentText4);
			colorSetUnselected(fragmentText2);
			colorSetUnselected(fragmentText3);
			colorSetUnselected(fragmentText1);
			colorSetUnselected(fragmentText5);
			break;
		case R.id.textView5:
			trans.replace(R.id.contentLayout, fragment5);
			colorSetSelected(fragmentText5);
			colorSetUnselected(fragmentText2);
			colorSetUnselected(fragmentText3);
			colorSetUnselected(fragmentText4);
			colorSetUnselected(fragmentText1);
			break;
		}
		
		trans.commit();
	}
	
	/** 
	 * ���ı���ɫ��������ɫ����Ϊ�ѱ�ѡ���״̬��
	 * 
	 * @param v TextView
	 */
	private void colorSetSelected(TextView v) {
		v.setTextColor(Color.BLACK);
		v.setBackgroundColor(Color.WHITE);
	}
	
	/** 
	 * ���ı���ɫ��������ɫ����Ϊδ��ѡ���״̬��
	 * 
	 * @param v TextView
	 */
	private void colorSetUnselected(TextView v) {
		v.setTextColor(Color.WHITE);
		v.setBackgroundColor(Color.BLACK);
	}
}