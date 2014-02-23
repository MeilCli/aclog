package info.re4k.asfc.aclog.impl;

import info.re4k.asfc.aclog.UserDetail;
import info.re4k.asfc.aclog.json.JSONObject;

public class UserDetailImpl implements UserDetail{

	private long favoritesCount = -1;
	private long retweetsCount = -1;
	private long userId = -1;

	public UserDetailImpl(JSONObject res) throws Exception{
		init(res);
	}

	private void init(JSONObject json) throws Exception{
		favoritesCount = json.getLong("favorites_count");
		retweetsCount = json.getLong("retweets_count");
		userId = json.getLong("id");
	}

	@Override
	public long getUserId(){
		return userId;
	}

	@Override
	public long getFavoritesCount(){
		return favoritesCount;
	}

	@Override
	public long getRetweetsCount(){
		return retweetsCount;
	}

}
