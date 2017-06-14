package com.gearreald.pushbullet_api;

public class Push {

	private Authenticator auth;
	private String id;
	private Device device;
	
	public Push(Authenticator auth){
		this.auth=auth;
	}
	
	public Push(Device d, Authenticator auth){
		this(auth);
		this.device=d;
	}
	
	public Push(String id, Authenticator auth){
		this(auth);
		this.id=id;
	}
	public Push(String id, Device d, Authenticator auth){
		this(d,auth);
		this.id=id;
	}
}
