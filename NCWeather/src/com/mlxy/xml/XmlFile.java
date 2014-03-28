package com.mlxy.xml;

import java.io.File;

import android.os.Environment;

/** 
 * �����࣬����ʵ�ּ���ȡFile���͵�xml�ļ�����
 * 
 * @author mlxy
 *
 */
public class XmlFile {
	private static File xmlFile = null;
	private static String fileName = "xml_resource.xml";
	
	private XmlFile() {}
	
	/** 
	 * ����xml�ļ�����
	 * 
	 * @return xmlFile */
	public static synchronized File getFile() {
		if (xmlFile == null) {
			// ��ȡ�ⲿ�洢·����ȡ���ļ�����
			File externalDirectory = Environment.getExternalStorageDirectory();
			xmlFile = new File(externalDirectory, fileName);
		}
		
		return xmlFile;
	}
}
