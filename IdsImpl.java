package info.re4k.asfc.aclog;

import org.json.JSONObject;

public class IdsImpl implements Ids{

	private int favoritesCount;
	private int retweetsCount;
	private long userId;

	public IdsImpl(JSONObject res) throws Exception{
		init(res);
	}

	private void init(JSONObject json) throws Exception{
		favoritesCount = json.getInt("favorites_count");
		retweetsCount = json.getInt("retweets_count");
		userId = json.getLong("user_id");
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