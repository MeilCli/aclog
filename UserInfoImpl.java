package com.fc2.blog.uameildi401.asfc4.aclog;

import org.json.JSONObject;

public class UserInfoImpl implements UserInfo{

	private User user;
	private int favoritedCount;
	private int retweetedCount;
	
	public UserInfoImpl(String res) throws Exception{
		JSONObject json = new JSONObject(res);
		init(json);
	}

	private void init(JSONObject json) throws Exception{
		user = new UserImpl(json);
		if(!json.isNull("stats")){
			JSONObject status = json.getJSONObject("stats");
			favoritedCount = status.getInt("favorited_count");
			retweetedCount = status.getInt("retweeted_count");
		}
	}
	
	@Override
	public User getUser(){
		return user;
	}

	@Override
	public int getFavoritedCount(){
		return favoritedCount;
	}

	@Override
	public int getRetweetedCount(){
		return retweetedCount;
	}
	
	@Override
	public String toString(){
		return "user="+user.toString()+",favoritedCount="+favoritedCount+",retweetedCount="+retweetedCount;
	}
}