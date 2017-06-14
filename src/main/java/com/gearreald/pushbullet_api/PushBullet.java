package com.gearreald.pushbullet_api;

import com.gearreald.pushbullet_api.exceptions.PushBulletException;

public class PushBullet {

	private final Authenticator auth; 
	
	public PushBullet(String apiKey) throws PushBulletException{
		this.auth = new Authenticator(apiKey);
		System.out.println(this.auth.toString());
	}
}
