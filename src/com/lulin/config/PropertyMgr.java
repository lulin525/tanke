package com.lulin.config;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置类
 */
public class PropertyMgr {
	static Properties props = new Properties();
	
	static {
		try {
            //把一个资源当静态文件读出来——config是直接在src目录下的配置文件
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object get(String key) {
		if(props == null) return null;
		return props.get(key);
	}
	
	//int getInt(key)
	//getString(key)
	public static void main(String[] args) {
		System.out.println(PropertyMgr.get("initTankeCount"));
		
	}
}
