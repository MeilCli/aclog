package info.re4k.asfc.aclog.impl;

import info.re4k.asfc.aclog.UserDetail;
import info.re4k.asfc.aclog.json.JSONObject;

public class UserDetailImpl implements UserDetail{

	private int favoritesCount;
	private int retweetsCount;
	private long userId = -1;

	public UserDetailImpl(JSONObject res) throws Exception{
		init(res);
	}

	private void init(JSONObject json) throws Exception{
		favoritesCount = json.getInt("favorites_count");
		retweetsCount = json.getInt("retweets_count");
		userId = json.getLong("id");
	}

	@Override
	public long getUserId(){
		return userId;
	}

	@Override
	public int getFavoritesCount(){
		return favoritesCount;
	}

	@Override
	public int getRetweetsCount(){
		return retweetsCount;
	}

}
