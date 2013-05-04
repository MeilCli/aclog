package com.fc2.blog.uameildi401.asfc4.aclog;

import org.json.JSONObject;

public class UserStatsImpl implements UserStats{

	private long userId;
	private int favoritedCount;
	private int retweetedCount;

	public UserStatsImpl(String res) throws Exception{
		JSONObject json = new JSONObject(res);
		init(json);
	}

	private void init(JSONObject json) throws Exception{
		userId = json.getLong("id");
		favoritedCount = json.getInt("favorited_count");
		retweetedCount = json.getInt("retweeted_count");
	}

	@Override
	public long getUserId(){
		return userId;
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
		return "user="+userId+",favoritedCount="+favoritedCount+",retweetedCount="+retweetedCount;
	}
}