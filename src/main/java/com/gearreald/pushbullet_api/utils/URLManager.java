package com.gearreald.pushbullet_api.utils;

import java.io.IOException;
import java.util.Properties;

public class URLManager {
	private static final Properties p;
	
	static {
		p = new Properties();
		try {
			p.load(URLManager.class.getClassLoader().getResourceAsStream("config/urls.properties"));
		} catch (IOException e) {
		}
	}
	
	public static String getURLFor(String urlName){
		return p.getProperty(urlName);
	}
}
