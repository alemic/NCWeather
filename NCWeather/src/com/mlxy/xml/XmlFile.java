package com.mlxy.xml;

import java.io.File;

import android.os.Environment;

public class XmlFile {
	private static File xmlFile;
	private static String fileName = "xml_resource.xml";
	
	private XmlFile() {}
	
	public static synchronized File getFile() {
		// 获取外部存储路径并取得文件对象。
		File externalDirectory = Environment.getExternalStorageDirectory();
		xmlFile = new File(externalDirectory, fileName);
		
		return xmlFile;
	}
}
