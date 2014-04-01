package com.mlxy.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.util.Xml;

public class XmlParser {
	private Context parent;
	private XmlPullParser parser;
	private Map<String, String> map;
	private String result;

	public XmlParser(Context parent) {
		this.parent = parent;
		this.parser = Xml.newPullParser();
	}

	/**
	 * 根据对应的标签检索标签内的内容。
	 * 
	 * @param tag 节点名称
	 * @return 返回标签内容，如果没有找到对应标签就返回空白字符。
	 */
	public String getContentByTag(String tag) throws Exception {
		// 初始化输入流，指定编码。
		InputStream in = parent.openFileInput(XmlFile.getFile().getName());
		parser.setInput(in, "UTF-8");
		result = "";

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

		// 释放资源。
		in.close();
		return result;
	}

	/**
	 * 根据给定的标签列表将对应的内容依次填入Map中并返回。
	 * 
	 * @param list 标签列表
	 * @return map 标签与内容一一对应的映射。
	 */
	public Map<String, String> getContentsByTags(String... tags)
			throws Exception {
		// 初始化输入流，指定编码。
		InputStream in = parent.openFileInput(XmlFile.getFile().getName());
		parser.setInput(in, "UTF-8");
		map = new HashMap<String, String>();

		String tagName = null;
		int nulls = 0;

		// 第一次获取事件。
		int eventType = parser.getEventType();

		// 当指针没有到文档结束位置的时候。
		while (eventType != XmlPullParser.END_DOCUMENT) {

			// 如果发现了标签开始处。
			if (eventType == XmlPullParser.START_TAG) {
				// 记下当前标签的名称。
				tagName = parser.getName();

				// 开始遍历数组。
				for (int i = 0; i < tags.length; i++) {
					// 如果当前位置是null，就进行下一项for循环。
					if (tags[i] == null) {
						nulls++;
						continue;
					}

					// 找到对应的标签就取出内容，添加进映射里。然后把数组的当前位置改为null并继续寻找下一个标签。
					if (tags[i].equalsIgnoreCase(tagName)) {
						map.put(tags[i], parser.nextText());
						tags[i] = null;
						break;
					}

				}

			}

			// 如果数组已经处理完毕就停止处理文档。
			if (nulls == tags.length) {
				break;
			} else {
				nulls = 0;
			}

			// 否则就继续循环。
			eventType = parser.next();
		}

		// 处理完毕，释放资源。
		in.close();

		return map;
	}

	/** 获取注释中的时间信息并格式化返回。 */
	public String getUpdateTime() {
		// 初始化输入流，指定编码。
		InputStream in = null;
		try {
			in = parent.openFileInput(XmlFile.getFile().getName());
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
			// 释放资源。
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (result.length() != 0) {
			result = "更新时间：" + result.substring(13);
		} else {
			result = "更新时间：未知";
		}
		
		return result;
	}
}
