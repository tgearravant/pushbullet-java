package com.gearreald.pushbullet_api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Instant;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gearreald.pushbullet_api.exceptions.PushBulletException;
import com.gearreald.pushbullet_api.utils.URLManager;

import net.tullco.tullutils.NetworkUtils;
import net.tullco.tullutils.Pair;

public class Device {
	
	private Authenticator auth;
	
	private String iden;
	private boolean active;
	private int appVersion;
	private Instant created;
	private Instant modified;
	private String icon;
	
	public Device(JSONObject json, Authenticator auth){
		this.auth=auth;
	}
	public Device(String id, Authenticator auth){
		
	}
	public static Set<Device> listDevices(Authenticator auth) throws PushBulletException{
		try {
			String s = NetworkUtils.getDataFromURL(URLManager.getURLFor("device.list"), true, NetworkUtils.GET, Pair.<String,String>of("Access-Token", auth.getApiKey()));
			JSONObject json = new JSONObject(s);
			JSONArray devicesJSON = json.getJSONArray("devices");
			Set<Device> devices = new HashSet<Device>();
			for(int i=0; i < devicesJSON.length(); i++){
				devicesJSON.get(i);
			}
		} catch (IOException e) {
			throw new PushBulletException(e); 
		}
	}
}
