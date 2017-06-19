package com.gearreald.pushbullet_api;

import java.util.Set;

import com.gearreald.pushbullet_api.exceptions.PushBulletException;


public class PushBullet {

	private final Server server;
	
	public PushBullet(String apiKey) throws PushBulletException{
		this.server=new Server(apiKey);
	}
	public User getUserData() throws PushBulletException{
		return User.getUser(this.server);
	}
	public Set<Device> getDevices() throws PushBulletException{
		return Device.listDevices(this.server);
	}
	public Device getDeviceByIden(String iden) throws PushBulletException {
		return Device.getDevice(this.server, iden);
	}
	public Set<Push> getHistoricalPushes(int limit) throws PushBulletException{
		return Push.getHistoricalPushes(this.server, limit);
	}
	public Device getDeviceWithNickname(String s) throws PushBulletException{
		for(Device d: this.getDevices()){
			if(d.getNickname().equals(s))
				return d;
		}
		return null;
	}
	public Push sendLinkToDevice(Device d, String title, String message, String link) throws PushBulletException{
		return Push.sendLinkToDevice(this.server, d, title, message, link);
	}
}
