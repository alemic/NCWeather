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
	 * 根据对应的标签检索标签内的内容。
	 * 
	 * @param tag 节点名称
	 * @return 返回标签内容，如果没有找到对应标签就返回null。*/
	public String getContentByTag(String tag) throws Exception {
		// 初始化输入流，指定编码。
		InputStream in = parent.openFileInput(xmlFile.getName());
		parser.setInput(in, "UTF-8");
		String result = "";
		
		// 第一次获取事件。
		int eventType = parser.getEventType();

		// 当指针没有到文档结束位置的时候。
		while (eventType != XmlPullParser.END_DOCUMENT) {
			// 如果发现了对应的标签就返回其中内容，然后跳出。
			if (eventType == XmlPullParser.START_TAG && tag.equalsIgnoreCase(parser.getName())) {
				result = parser.nextText();
				break;
			}
			
			// 否则就继续循环。
			eventType = parser.next();
		}

		//释放资源。
		in.close();
		return result;
	}
	
	/** 返回格式化后的温度字符串。*/
	public String getTemperature() {
		String temDay = "";
		String temNight = "";
		
		try {
			temDay = this.getContentByTag("temperature1");
			temNight = this.getContentByTag("temperature2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "白天：" + temDay + "℃\n夜间：" + temNight + "℃"; 
	}
	
	/** 返回格式化后的天气状况字符串。*/
	public String getWeather() {
		String weaDay = "";
		String weaNight = "";
		
		try {
			weaDay = this.getContentByTag("status1");
			weaNight = this.getContentByTag("status2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "白天：" + weaDay + "\n夜间：" + weaNight; 
	}
	
	/** 获取注释中的时间信息并格式化返回。*/
	public String getUpdateTime() {
		// 初始化输入流，指定编码。
		InputStream in = null;
		try {
			in = parent.openFileInput(xmlFile.getName());
			parser.setInput(in, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String result = "";
		
		try {
			
			// 第一次获取事件。
			int eventType = parser.getEventType();
			
			// 在文档读完之前。
			while (eventType != XmlPullParser.END_DOCUMENT) {
				// 如果发现了注释就把其中内容传入字符串变量，然后跳出。
				if (eventType == XmlPullParser.COMMENT) {
					result = parser.getText();
					break;
				}

				// 否则就继续寻找下个标记。
				eventType = parser.nextToken();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放资源。
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		result = "更新时间：" + result.substring(13);
		return result;
	}
}