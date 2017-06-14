package com.gearreald.pushbullet_api;

import java.io.IOException;
import java.time.Instant;

import org.json.JSONObject;

import com.gearreald.pushbullet_api.exceptions.PushBulletException;
import com.gearreald.pushbullet_api.utils.URLManager;

import net.tullco.tullutils.NetworkUtils;
import net.tullco.tullutils.Pair;

public class Authenticator {
	
	private final String apiKey;
	private final JSONObject authResponseJSON;
	
	public Authenticator(String apiKey) throws PushBulletException{
		this.apiKey=apiKey;
		try{
			String response = NetworkUtils.getDataFromURL(URLManager.getURLFor("authentication"), true, NetworkUtils.GET, Pair.<String,String>of("Access-Token",this.apiKey));
			this.authResponseJSON = new JSONObject(response);
		}catch(IOException e){
			throw new PushBulletException(e);
		}
	}
	public String getApiKey(){
		return this.apiKey;
	}
	public String getEmail(){
		return this.authResponseJSON.getString("email");
	}
	public Instant getUserCreatedTime(){
		return Instant.ofEpochSecond(this.authResponseJSON.getLong("created"));
	}
	@Override
	public String toString(){
		return this.authResponseJSON.toString();
	}
}
