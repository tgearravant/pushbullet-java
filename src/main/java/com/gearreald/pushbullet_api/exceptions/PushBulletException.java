package com.gearreald.pushbullet_api.exceptions;

public class PushBulletException extends Exception {

	private static final long serialVersionUID = -6582878257213885131L;

	public PushBulletException(){};
	public PushBulletException(String message){
		super(message);
	}
	public PushBulletException(Throwable cause){
		super(cause);
	}
	public PushBulletException(String message,Throwable cause){
		super(message,cause);
	}
	
}
