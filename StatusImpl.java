package com.fc2.blog.uameildi401.asfc4.aclog;

import org.json.JSONArray;
import org.json.JSONObject;

public class StatusImpl implements Status{
	private long id;
	private User user;
	private int favoritesCount;
	private int retweetsCount;
	private User[] favoritesUser;
	private User[] retweetsUser;

	public StatusImpl(String res) throws Exception{
		JSONObject json = new JSONObject(res);
		init(json);
	}

	public StatusImpl(JSONObject res) throws Exception{
		init(res);
	}

	private void init(JSONObject json) throws Exception{
		id = json.getLong("id");
		if(!json.isNull("user")){
			user = new UserImpl(json.getJSONObject("user"));
		}
		favoritesCount = json.getInt("favorites_count");
		retweetsCount = json.getInt("retweets_count");
		if(!json.isNull("favorites")){
			JSONArray favorites = json.getJSONArray("favorites");
			int fi = favorites.length();
			favoritesUser = new User[fi];
			for(int i = 0;i<fi;i++){
				JSONObject users = favorites.getJSONObject(i);
				if(!users.isNull("user")){
					favoritesUser[i] = new UserImpl(users.getJSONObject("user"));
				}
			}
		}else{
			favoritesUser = new User[0];
		}
		if(!json.isNull("retweets")){
			JSONArray favorites = json.getJSONArray("retweets");
			int ri = favorites.length();
			retweetsUser = new User[ri];
			for(int i = 0;i<ri;i++){
				JSONObject users = favorites.getJSONObject(i);
				if(!users.isNull("user")){
					retweetsUser[i] = new UserImpl(users.getJSONObject("user"));
				}
			}
		}else{
			retweetsUser = new User[0];
		}
	}

	@Override
	public long getId(){
		return id;
	}

	@Override
	public User getUser(){
		return user;
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
	public User[] getFavoritesUser(){
		return favoritesUser;
	}

	@Override
	public User[] getRetweetsUser(){
		return retweetsUser;
	}

	@Override
	public String toString(){
		return "id="+id+",user="+user.toString()+",favoritesCount="+favoritesCount+",retweetsCount="+retweetsCount+",favoritesUser="
				+favoritesUser.toString()+",retweetsUser="+retweetsUser.toString();
	}

}