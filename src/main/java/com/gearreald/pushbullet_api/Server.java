package com.gearreald.pushbullet_api;

import java.io.IOException;

import org.json.JSONObject;

import net.tullco.tullutils.NetworkUtils;
import net.tullco.tullutils.Pair;

class Server {
	private final String apiKey;
	
	protected Server(String apiKey){
		this.apiKey=apiKey;
	}
	protected JSONObject getRequest(String url) throws IOException{
		String response = NetworkUtils.getDataFromURL(url
				,true
				,NetworkUtils.GET
				,Pair.<String,String>of("Access-Token",apiKey));
		return new JSONObject(response);
	}
	
	protected JSONObject postRequest(String url, JSONObject json) throws IOException{
		String response = NetworkUtils.sendDataToURL(url
				,true
				,NetworkUtils.POST
				,json.toString()
				,Pair.<String,String>of("Content-Type", "application/json")
				,Pair.<String,String>of("Access-Token", apiKey));		
		return new JSONObject(response);
	}
	
	protected JSONObject putRequest(String url, JSONObject json) throws IOException{
		String response = NetworkUtils.sendDataToURL(url
				,true
				,NetworkUtils.PUT
				,json.toString()
				,Pair.<String,String>of("Content-Type", "application/json")
				,Pair.<String,String>of("Access-Token", apiKey));		
		return new JSONObject(response);
	}
	
	protected JSONObject deleteRequest(String url, JSONObject json) throws IOException{
		String response = NetworkUtils.sendDataToURL(url
				,true
				,NetworkUtils.DELETE
				,json.toString()
				,Pair.<String,String>of("Content-Type", "application/json")
				,Pair.<String,String>of("Access-Token", apiKey));		
		return new JSONObject(response);
	}
}
