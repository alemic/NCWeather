package com.mlxy.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.util.Xml;

public class OldXmlParser {
	private Context parent;
	private File xmlFile;
	private XmlPullParser parser;
	
	public OldXmlParser(Context parent, File xmlFile) {
		this.parent = parent;
		this.xmlFile = xmlFile;
		this.parser = Xml.newPullParser();
	}
	
	/** 
	 * ���ݶ�Ӧ�ı�ǩ������ǩ�ڵ����ݡ�
	 * 
	 * @param tag �ڵ�����
	 * @return ���ر�ǩ���ݣ����û���ҵ���Ӧ��ǩ�ͷ���null��*/
	public String getContentByTag(String tag) throws Exception {
		// ��ʼ����������ָ�����롣
		InputStream in = parent.openFileInput(xmlFile.getName());
		parser.setInput(in, "UTF-8");
		String result = "";
		
		// ��һ�λ�ȡ�¼���
		int eventType = parser.getEventType();

		// ��ָ��û�е��ĵ�����λ�õ�ʱ��
		while (eventType != XmlPullParser.END_DOCUMENT) {
			// ��������˶�Ӧ�ı�ǩ�ͷ����������ݣ�Ȼ��������
			if (eventType == XmlPullParser.START_TAG && tag.equalsIgnoreCase(parser.getName())) {
				result = parser.nextText();
				break;
			}
			
			// ����ͼ���ѭ����
			eventType = parser.next();
		}

		//�ͷ���Դ��
		in.close();
		return result;
	}
	
	/** ���ظ�ʽ������¶��ַ�����*/
	public String getTemperature() {
		String temDay = "";
		String temNight = "";
		
		try {
			temDay = this.getContentByTag("temperature1");
			temNight = this.getContentByTag("temperature2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "���죺" + temDay + "��\nҹ�䣺" + temNight + "��"; 
	}
	
	/** ���ظ�ʽ���������״���ַ�����*/
	public String getWeather() {
		String weaDay = "";
		String weaNight = "";
		
		try {
			weaDay = this.getContentByTag("status1");
			weaNight = this.getContentByTag("status2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "���죺" + weaDay + "\nҹ�䣺" + weaNight; 
	}
	
	/** ��ȡע���е�ʱ����Ϣ����ʽ�����ء�*/
	public String getUpdateTime() {
		// ��ʼ����������ָ�����롣
		InputStream in = null;
		try {
			in = parent.openFileInput(xmlFile.getName());
			parser.setInput(in, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String result = "";
		
		try {
			
			// ��һ�λ�ȡ�¼���
			int eventType = parser.getEventType();
			
			// ���ĵ�����֮ǰ��
			while (eventType != XmlPullParser.END_DOCUMENT) {
				// ���������ע�;Ͱ��������ݴ����ַ���������Ȼ��������
				if (eventType == XmlPullParser.COMMENT) {
					result = parser.getText();
					break;
				}

				// ����ͼ���Ѱ���¸���ǡ�
				eventType = parser.nextToken();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//�ͷ���Դ��
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		result = "����ʱ�䣺" + result.substring(13);
		return result;
	}
}