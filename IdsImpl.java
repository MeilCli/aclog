package com.fc2.blog.uameildi401.asfc4.aclog;

import org.json.JSONObject;

public class IdsImpl implements Ids{

	private int count;
	private User user;

	public IdsImpl(JSONObject res) throws Exception{
		init(res);
	}

	private void init(JSONObject json) throws Exception{
		count = json.getInt("count");
		if(!json.isNull("user")){
			user = new UserImpl(json.getJSONObject("user"));
		}
	}

	@Override
	public int getCount(){
		return count;
	}

	@Override
	public User getUser(){
		return user;
	}

	@Override
	public String toString(){
		return "count="+count+",user="+user.toString();
	}
}