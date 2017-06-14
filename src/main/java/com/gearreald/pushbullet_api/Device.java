package com.gearreald.pushbullet_api;

import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gearreald.pushbullet_api.exceptions.PushBulletException;
import com.gearreald.pushbullet_api.utils.URLManager;

public class Device {
	
	private final String iden;
	private final String nickname;
	private final boolean active;
	private final int appVersion;
	private final Instant created;
	private final Instant modified;
	private final String icon;
	
	private Device(JSONObject json){
		this.iden=json.getString("iden");
		this.active=json.getBoolean("active");
		this.appVersion=json.getInt("app_version");
		this.created=Instant.ofEpochSecond(json.getLong("created"));
		this.modified=Instant.ofEpochSecond(json.getLong("modified"));
		this.icon=json.getString("icon");
		this.nickname=json.getString("nickname");
	}
	public String getIden(){
		return iden;
	}
	public boolean isActive() {
		return active;
	}
	public int getAppVersion() {
		return appVersion;
	}
	public Instant getCreated() {
		return created;
	}
	public Instant getModified() {
		return modified;
	}
	public String getIcon() {
		return icon;
	}
	public String getNickname() {
		return this.nickname;
	}
	public static Device getDevice(Server s, String iden) throws PushBulletException{
		try {
			JSONObject json = s.getRequest(String.format(URLManager.getURLFor("device.find"),iden));
			return new Device(json);
		} catch (IOException e) {
			throw new PushBulletException(e); 
		}
	}
	public static Set<Device> listDevices(Server s) throws PushBulletException{
		Set<Device> devices = new HashSet<Device>();
		try {
			JSONObject json = s.getRequest(URLManager.getURLFor("device.list"));
			JSONArray devicesJSON = json.getJSONArray("devices");
			for(int i=0; i < devicesJSON.length(); i++){
				devices.add(new Device(devicesJSON.getJSONObject(i)));
			}
		} catch (IOException e) {
			throw new PushBulletException(e); 
		}
		return devices;
	}
}
