package com.gearreald.pushbullet_api;

import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.gearreald.pushbullet_api.exceptions.PushBulletException;
import com.gearreald.pushbullet_api.utils.URLManager;

public class Push {

	private final boolean active;
	private final boolean dismissed;
	private final String deviceIden;
	private final String iden;
	private final String type;
	private final Instant created;
	private final Instant modified;

	protected Push(JSONObject json){
		this.active = json.getBoolean("active");
		this.dismissed = json.getBoolean("dismissed");
		this.iden = json.getString("iden");
		this.type = json.getString("type");
		this.deviceIden = json.getString("target_device_iden");
		this.created=Instant.ofEpochSecond(json.getLong("created"));
		this.modified=Instant.ofEpochSecond(json.getLong("modified"));
	}
	
	public boolean isActive() {
		return active;
	}

	public boolean isDismissed() {
		return dismissed;
	}

	public String getDeviceIden() {
		return deviceIden;
	}

	public String getIden() {
		return iden;
	}

	public String getType() {
		return type;
	}

	public Instant getCreated() {
		return created;
	}

	public Instant getModified() {
		return modified;
	}

	public static Set<Push> getHistoricalPushes(Server s, int limit) throws PushBulletException{
		Set<Push> pushes = new HashSet<Push>();
		try {
			String cursor=null;
			do{
				JSONObject json;
				if(cursor==null)
					json = s.getRequest(URLManager.getURLFor("push.list")+"?limit=100");
				else
					json = s.getRequest(URLManager.getURLFor("push.list")+"?limit=100&cursor="+cursor);
				System.out.println(cursor);
				addPushesToSet(pushes, json, limit);
				try{
					cursor = json.getString("cursor");
				}catch(JSONException e){
					cursor = null;
				}
			} while(cursor!=null && pushes.size()<limit);
		} catch (IOException e) {
			throw new PushBulletException(e); 
		}
		return pushes;
	}
	
	private static void addPushesToSet(Set<Push> set, JSONObject json, int limit){
		JSONArray pushesJSON = json.getJSONArray("pushes");
		for(int i=0; i < pushesJSON.length(); i++){
			if(set.size()>= limit)
				break;
			set.add(new Push(pushesJSON.getJSONObject(i)));
		}
	}

	public static Push sendMessageToDevice(Server s, Device d, String title, String message) throws PushBulletException {
		if(message==null)
			throw new PushBulletException("This request must have a message");
		if(d==null)
			throw new PushBulletException("The device must not be null");
		JSONObject requestJSON = new JSONObject();
		requestJSON.put("device_iden",d.getIden());
		requestJSON.put("type", "note");
		if(title!=null){
			requestJSON.put("title", title);
		}
		requestJSON.put("body", message);
		try {
			return new Push(s.postRequest(URLManager.getURLFor("push.create"), requestJSON));
		} catch (IOException e) {
			throw new PushBulletException(e);
		}
	}
	
	public static Push sendLinkToDevice(Server s, Device d, String title, String message, String link) throws PushBulletException{
		if(link==null)
			throw new PushBulletException("This request must have a link");
		if(d==null)
			throw new PushBulletException("The device must not be null");
		JSONObject requestJSON = new JSONObject();
		requestJSON.put("device_iden",d.getIden());
		requestJSON.put("type", "link");
		if(title!=null){
			requestJSON.put("title", title);
		}
		if(message!=null){
			requestJSON.put("body", message);
		}
		requestJSON.put("url", link);
		try {
			return new Push(s.postRequest(URLManager.getURLFor("push.create"), requestJSON));
		} catch (IOException e) {
			throw new PushBulletException(e);
		}
	}
}
