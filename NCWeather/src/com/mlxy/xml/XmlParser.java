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
	 * ���ݶ�Ӧ�ı�ǩ������ǩ�ڵ����ݡ�
	 * 
	 * @param tag �ڵ�����
	 * @return ���ر�ǩ���ݣ����û���ҵ���Ӧ��ǩ�ͷ��ؿհ��ַ���
	 */
	public String getContentByTag(String tag) throws Exception {
		// ��ʼ����������ָ�����롣
		InputStream in = parent.openFileInput(XmlFile.getFile().getName());
		parser.setInput(in, "UTF-8");
		result = "";

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

		// �ͷ���Դ��
		in.close();
		return result;
	}

	/**
	 * ���ݸ����ı�ǩ�б���Ӧ��������������Map�в����ء�
	 * 
	 * @param list ��ǩ�б�
	 * @return map ��ǩ������һһ��Ӧ��ӳ�䡣
	 */
	public Map<String, String> getContentsByTags(String... tags)
			throws Exception {
		// ��ʼ����������ָ�����롣
		InputStream in = parent.openFileInput(XmlFile.getFile().getName());
		parser.setInput(in, "UTF-8");
		map = new HashMap<String, String>();

		String tagName = null;
		int nulls = 0;

		// ��һ�λ�ȡ�¼���
		int eventType = parser.getEventType();

		// ��ָ��û�е��ĵ�����λ�õ�ʱ��
		while (eventType != XmlPullParser.END_DOCUMENT) {

			// ��������˱�ǩ��ʼ����
			if (eventType == XmlPullParser.START_TAG) {
				// ���µ�ǰ��ǩ�����ơ�
				tagName = parser.getName();

				// ��ʼ�������顣
				for (int i = 0; i < tags.length; i++) {
					// �����ǰλ����null���ͽ�����һ��forѭ����
					if (tags[i] == null) {
						nulls++;
						continue;
					}

					// �ҵ���Ӧ�ı�ǩ��ȡ�����ݣ���ӽ�ӳ���Ȼ�������ĵ�ǰλ�ø�Ϊnull������Ѱ����һ����ǩ��
					if (tags[i].equalsIgnoreCase(tagName)) {
						map.put(tags[i], parser.nextText());
						tags[i] = null;
						break;
					}

				}

			}

			// ��������Ѿ�������Ͼ�ֹͣ�����ĵ���
			if (nulls == tags.length) {
				break;
			} else {
				nulls = 0;
			}

			// ����ͼ���ѭ����
			eventType = parser.next();
		}

		// ������ϣ��ͷ���Դ��
		in.close();

		return map;
	}

	/** ��ȡע���е�ʱ����Ϣ����ʽ�����ء� */
	public String getUpdateTime() {
		// ��ʼ����������ָ�����롣
		InputStream in = null;
		try {
			in = parent.openFileInput(XmlFile.getFile().getName());
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
			// �ͷ���Դ��
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (result.length() != 0) {
			result = "����ʱ�䣺" + result.substring(13);
		} else {
			result = "����ʱ�䣺δ֪";
		}
		
		return result;
	}
}
