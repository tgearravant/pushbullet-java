package com.gearreald.pushbullet_api;

import java.io.IOException;
import java.time.Instant;

import org.json.JSONObject;

import com.gearreald.pushbullet_api.exceptions.PushBulletException;
import com.gearreald.pushbullet_api.utils.URLManager;

public class User {
	
	private final Instant created;
	private final Instant modified;
	private final String email;
	private final String iden;
	private final String name;
	private final long maxUploadSize;
	
	private User(JSONObject json) throws PushBulletException{
		this.email=json.getString("email");
		this.iden=json.getString("iden");
		this.name=json.getString("name");
		this.maxUploadSize=json.getLong("max_upload_size");
		this.created=Instant.ofEpochSecond(json.getLong("created"));
		this.modified=Instant.ofEpochSecond(json.getLong("modified"));
	}
	public String getEmail(){
		return this.email;
	}
	public String getIden(){
		return this.iden;
	}
	public String getName(){
		return this.name;
	}
	public long getMaxUploadSize(){
		return this.maxUploadSize;
	}
	public Instant getCreated(){
		return this.created;
	}
	public Instant getModified(){
		return this.modified;
	}
	
	public static User getUser(Server s) throws PushBulletException{
		try {
			return new User(s.getRequest(URLManager.getURLFor("authentication")));
		} catch (IOException e) {
			throw new PushBulletException(e);
		}
	}
}
