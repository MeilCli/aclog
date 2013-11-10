package info.re4k.asfc.aclog;

import org.json.JSONObject;

public class UserImpl implements User{

	private String screenName;
	private long userId;

	public UserImpl(JSONObject res) throws Exception{
		init(res);
	}

	private void init(JSONObject json) throws Exception{
		screenName = json.getString("screen_name");
		userId = json.getLong("id");
	}

	@Override
	public String getScreenName(){
		return screenName;
	}

	@Override
	public long getUserId(){
		return userId;
	}

	@Override
	public String toString(){
		return "screenname="+screenName+",userid="+userId;
	}
}