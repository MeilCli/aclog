package info.re4k.asfc.aclog;

import org.json.JSONArray;
import org.json.JSONObject;

public class StatusImpl implements Status{
	private long id;
	private long userId;
	private int favoritesCount;
	private int retweetsCount;
	private long[] favoritesUserId;
	private long[] retweetsUserId;

	public StatusImpl(String res) throws Exception{
		JSONObject json = new JSONObject(res);
		init(json);
	}

	public StatusImpl(JSONObject res) throws Exception{
		init(res);
	}

	private void init(JSONObject json) throws Exception{
		id = json.getLong("id");
		userId = json.getLong("user_id");
		favoritesCount = json.getInt("favorites_count");
		retweetsCount = json.getInt("retweets_count");
		if(!json.isNull("favoriters")){
			JSONArray favorites = json.getJSONArray("favoriters");
			int fi = favorites.length();
			favoritesUserId = new long[fi];
			for(int i = 0;i<fi;i++){
				favoritesUserId[i] = favorites.getLong(i);
			}
		}else{
			favoritesUserId = new long[0];
		}
		if(!json.isNull("retweeters")){
			JSONArray retweets = json.getJSONArray("retweeters");
			int ri = retweets.length();
			retweetsUserId = new long[ri];
			for(int i = 0;i<ri;i++){
				retweetsUserId[i] = retweets.getLong(i);
			}
		}else{
			retweetsUserId = new long[0];
		}
	}

	@Override
	public long getId(){
		return id;
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

	@Override
	public long[] getFavoritesUserId(){
		return favoritesUserId;
	}

	@Override
	public long[] getRetweetsUserId(){
		return retweetsUserId;
	}

}