package info.re4k.asfc.aclog.impl;

import info.re4k.asfc.aclog.UserDetail;
import info.re4k.asfc.aclog.json.JSONObject;

public class UserDetailImpl implements UserDetail{

	private int count = -1;
	private long userId = -1;

	public UserDetailImpl(JSONObject res) throws Exception{
		init(res);
	}

	private void init(JSONObject json) throws Exception{
		count = json.getInt("count");
		userId = json.getLong("id");
	}

	@Override
	public long getUserId(){
		return userId;
	}

	@Override
	public int getCount(){
		return count;
	}

}
