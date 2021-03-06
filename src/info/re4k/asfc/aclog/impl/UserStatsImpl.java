package info.re4k.asfc.aclog.impl;

import info.re4k.asfc.aclog.UserStats;
import info.re4k.asfc.aclog.json.JSONObject;

public class UserStatsImpl implements UserStats{

	private long userId = -1;
	private int reactionsCount;
	private boolean isRegistered = false;

	public UserStatsImpl(String res) throws Exception{
		JSONObject json = new JSONObject(res);
		init(json);
	}

	private void init(JSONObject json) throws Exception{
		userId = json.getLong("id");
		reactionsCount = json.getInt("reactions_count");
		isRegistered = json.getBoolean("registered");
	}

	@Override
	public long getUserId(){
		return userId;
	}

	@Override
	public int getReactionsCount(){
		return reactionsCount;
	}

	@Override
	public boolean isRegistered(){
		return isRegistered;
	}

}