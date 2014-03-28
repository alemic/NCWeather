package com.mlxy.xml;

import java.io.File;

import android.os.Environment;

/** 
 * 单例类，用于实现及获取File类型的xml文件对象。
 * 
 * @author mlxy
 *
 */
public class XmlFile {
	private static File xmlFile = null;
	private static String fileName = "xml_resource.xml";
	
	private XmlFile() {}
	
	/** 
	 * 返回xml文件对象。
	 * 
	 * @return xmlFile */
	public static synchronized File getFile() {
		if (xmlFile == null) {
			// 获取外部存储路径并取得文件对象。
			File externalDirectory = Environment.getExternalStorageDirectory();
			xmlFile = new File(externalDirectory, fileName);
		}
		
		return xmlFile;
	}
}
