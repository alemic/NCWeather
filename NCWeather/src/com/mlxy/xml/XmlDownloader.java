package com.mlxy.xml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.os.Environment;

import com.mlxy.util.CharacterProcesser;

/**
 * Xml文件下载器，重构完成。
 * 
 * @author mlxy
 * @version 1.1
 * */
public class XmlDownloader {
	private Context parent;
	
	private String cityName = "南昌";
	private String city = CharacterProcesser.encodeByGB2312(cityName);
	private String password = "DJOYnieT8234jlsK";
	private int day = 0;
	private String link = "http://php.weather.sina.com.cn/xml.php?city=" + city
			+ "&password=" + password + "&day=" + day;
	
	/** 私有构造方法，避免被外部实例化。*/
	private XmlDownloader() {
	}
	
	/** 根据给定的链接下载对应的xml文件。
	 * 
	 * @param link API的链接，需要标明网络协议。*/
	private void downloadXml(String link) {
		// 用链接字符串new出URL对象
		URL url = null;
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		// 获取外部存储路径并创建文件对象。
		File externalDirectory = Environment.getExternalStorageDirectory();
		String fileName = "xml_resource.xml";
		File file = new File(externalDirectory, fileName);

		// 写文件。
		
		// 初始化io流。
		InputStream in = null;
		BufferedReader reader = null;
		FileOutputStream out = null;
		BufferedWriter writer = null;
		
		try {
			
			// 建立连接并给io流赋值。
			in = (InputStream) url.getContent();
			reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
			out = this.parent.openFileOutput(file.getName(), Context.MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(out, "iso-8859-1"));
			
			// 读一行写一行，然后另起一行。
			String line = null;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				writer.flush();
				writer.close();
				out.close();
				reader.close();
				in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private void setParent(Context parent) {
		this.parent = parent;
	}
	
	private void setCity(String cityName) {
		this.cityName = cityName;
	}
	
	private void setPassword(String password) {
		this.password = password;
	}
	
	private void setDay(int day) {
		this.day = day;
	}
	
	private String getLink() {
		return this.link;
	}
	
	public static class Builder {
		/** 储存好的一个实例，留待建造完毕后返回。*/
		private XmlDownloader instance = new XmlDownloader();
		
		/** 构造函数，需要输入启动此构造器的上下文视图作为参数。*/
		public Builder(Context parent) {
			instance.setParent(parent);
		}
		
		/** 设置城市名。</br>
		 * 默认值为<b>南昌</b>。
		 * 
		 * @param cityName 城市名
		 * @return 构造器本身
		 */
		public Builder setCity(String cityName) {
			instance.setCity(cityName);
			return this;
		}
		
		/** 设置密码。</br>
		 * 默认值为<b>DJOYnieT8234jlsK</b>。
		 * 
		 * @param password API密码
		 * @return 构造器本身
		 */
		public Builder setPassword(String password) {
			instance.setPassword(password);
			return this;
		}
		
		/** 设置日期。</br>
		 * 默认值为<b>0</b>，也即是<b>今天</b>。</br>
		 * 以此类推。
		 * 
		 * @param day 日子，0为当天，范围在0-4
		 * @return 构造器本身
		 */
		public Builder setDay(int day) {
			instance.setDay(day);
			return this;
		}
		
		/** 用已经设置好的或默认的参数下载XML文件。*/
		public void download() {
			String link = instance.getLink();
			instance.downloadXml(link);
		}
	}
}
