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
 * Xml�ļ����������ع���ɡ�
 * 
 * @author mlxy
 * @version 1.1
 * */
public class XmlDownloader {
	private Context parent;
	
	private String cityName = "�ϲ�";
	private String city = CharacterProcesser.encodeByGB2312(cityName);
	private String password = "DJOYnieT8234jlsK";
	private int day = 0;
	private String link = "http://php.weather.sina.com.cn/xml.php?city=" + city
			+ "&password=" + password + "&day=" + day;
	
	/** ˽�й��췽�������ⱻ�ⲿʵ������*/
	private XmlDownloader() {
	}
	
	/** ���ݸ������������ض�Ӧ��xml�ļ���
	 * 
	 * @param link API�����ӣ���Ҫ��������Э�顣*/
	private void downloadXml(String link) {
		// �������ַ���new��URL����
		URL url = null;
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		// ��ȡ�ⲿ�洢·���������ļ�����
		File externalDirectory = Environment.getExternalStorageDirectory();
		String fileName = "xml_resource.xml";
		File file = new File(externalDirectory, fileName);

		// д�ļ���
		
		// ��ʼ��io����
		InputStream in = null;
		BufferedReader reader = null;
		FileOutputStream out = null;
		BufferedWriter writer = null;
		
		try {
			
			// �������Ӳ���io����ֵ��
			in = (InputStream) url.getContent();
			reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
			out = this.parent.openFileOutput(file.getName(), Context.MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(out, "iso-8859-1"));
			
			// ��һ��дһ�У�Ȼ������һ�С�
			String line = null;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// �ͷ���Դ
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
		/** ����õ�һ��ʵ��������������Ϻ󷵻ء�*/
		private XmlDownloader instance = new XmlDownloader();
		
		/** ���캯������Ҫ���������˹���������������ͼ��Ϊ������*/
		public Builder(Context parent) {
			instance.setParent(parent);
		}
		
		/** ���ó�������</br>
		 * Ĭ��ֵΪ<b>�ϲ�</b>��
		 * 
		 * @param cityName ������
		 * @return ����������
		 */
		public Builder setCity(String cityName) {
			instance.setCity(cityName);
			return this;
		}
		
		/** �������롣</br>
		 * Ĭ��ֵΪ<b>DJOYnieT8234jlsK</b>��
		 * 
		 * @param password API����
		 * @return ����������
		 */
		public Builder setPassword(String password) {
			instance.setPassword(password);
			return this;
		}
		
		/** �������ڡ�</br>
		 * Ĭ��ֵΪ<b>0</b>��Ҳ����<b>����</b>��</br>
		 * �Դ����ơ�
		 * 
		 * @param day ���ӣ�0Ϊ���죬��Χ��0-4
		 * @return ����������
		 */
		public Builder setDay(int day) {
			instance.setDay(day);
			return this;
		}
		
		/** ���Ѿ����úõĻ�Ĭ�ϵĲ�������XML�ļ���*/
		public void download() {
			String link = instance.getLink();
			instance.downloadXml(link);
		}
	}
}
