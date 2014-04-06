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
 * 主界面，应用目前唯一的Activity。
 * 
 * @author mlxy
 * */
public class MainActivity extends Activity implements OnClickListener {
	// 处理Fragment的组件。
	FragmentManager manager;
	FragmentTransaction trans;
	
	// 上半屏内容的数据。
	String cityString = "";
	String currentTemperatureString = "";
	String weatherString = "";
	String temperatureString = "";
	String updateTimeString = "";
	Bitmap weatherImage;
	
	// 上半屏内容的控件。
	TextView cityText;
	TextView currentTemperatureText;
	TextView weatherText;
	TextView temperatureText;
	ImageView weatherImageView;
	
	// 下半屏的选项卡。
	TextView fragmentText1;
	TextView fragmentText2;
	TextView fragmentText3;
	TextView fragmentText4;
	TextView fragmentText5;
	
	// 下半屏的具体说明内容。
	String content1;
	String content2;
	String content3;
	String content4;
	String content5;
	
	// 下半屏的几个Fragment。
	Fragment fragment1;
	Fragment fragment2;
	Fragment fragment3;
	Fragment fragment4;
	Fragment fragment5;
	
	/** 处理器，用于将信息更新到UI中。*/
	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			if (msg.what == 0x123) {
				// 更新上半屏的天气信息。
				cityText.setText(cityString);
				weatherText.setText(weatherString);
				weatherText.setHorizontallyScrolling(true);
				currentTemperatureText.setText(currentTemperatureString);
				temperatureText.setText(temperatureString);
				
				weatherImageView.setImageBitmap(weatherImage);
				
				// 向下半屏的几个Fragment传递内容。
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
				
				// 显示第一个Fragment。
				trans = manager.beginTransaction();
				trans.replace(R.id.contentLayout, fragment1);
				trans.commit();
				
				// 弹出提示框提示更新时间。
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
		
		// 获取上半屏组件对象。
		cityText = (TextView) findViewById(R.id.textCity);
		currentTemperatureText = (TextView) findViewById(R.id.textCurrentTemperature);
		weatherText = (TextView) findViewById(R.id.textWeather);
		temperatureText = (TextView) findViewById(R.id.textTemperature);
		
		weatherImageView = (ImageView) findViewById(R.id.imageView1);
		
		// 获取硬件参数，用以计算并设置上半屏UI字体。
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
		
		// 获取下半屏组件对象。
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
		
		// 设置选项卡字体，挂载监听器。
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
		
		// 启动两个线程，下载后更新内容。
		Thread download = new Thread(new DownloadXml());
		try {
			download.start();
			download.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
		
		this.updateInfo();
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
	
	/** 获取UI中所需的数据并给相应的变量赋值。*/
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
	 * 将文本颜色及背景颜色设置为已被选择的状态。
	 * 
	 * @param v TextView
	 */
	private void colorSetSelected(TextView v) {
		v.setTextColor(Color.BLACK);
		v.setBackgroundColor(Color.WHITE);
	}
	
	/** 
	 * 将文本颜色及背景颜色设置为未被选择的状态。
	 * 
	 * @param v TextView
	 */
	private void colorSetUnselected(TextView v) {
		v.setTextColor(Color.WHITE);
		v.setBackgroundColor(Color.BLACK);
	}
}