package com.fc2.blog.uameildi401.asfc4.aclog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Production environment is Android4.0
 * */
public class Aclog{

	public Aclog(){}

	private String toUrl(String url,Paging p){
		url += "?";
		url += "&page="+p.page;
		if(p.screenName!=null)
			url += "&screen_name="+p.screenName;
		if(p.userId!=0)
			url += "&user_id="+p.userId;
		if(p.screenName_b!=null)
			url += "&screen_name_b="+p.screenName_b;
		if(p.userId_b!=0)
			url += "&user_id_b="+p.userId_b;
		if(p.count!=0)
			url += "&count="+p.count;
		if(p.tweet_id!=0)
			url += "&id="+p.tweet_id;
		if(p.max_id!=0)
			url += "&max_id="+p.max_id;
		if(p.since_id!=0)
			url += "&since_id="+p.since_id;
		return url;
	}

	private String toUrl(String url,ArrayList<Long> ids){
		url += "?user_id=";
		for(int i = 0;i<ids.size();i++){
			if(i!=0){
				url += ",";
			}
			url += ids.get(i)+"";
		}
		return url;
	}

	private String get(String url) throws Exception{
		URL url2 = new URL(url);
		HttpURLConnection http = (HttpURLConnection)url2.openConnection();
		http.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
		String res = "";
		while(true){
			String line = reader.readLine();
			if(line==null){
				break;
			}
			res += line;
		}
		http.disconnect();
		reader.close();
		try{
			JSONObject json = new JSONObject(res);
			if(!json.isNull("error")){
				throw new Exception(json.getJSONObject("error").getString("message"));
			}
		}catch(Exception e){}
		return res;
	}

	private ArrayList<Status> toStatus(String res) throws Exception{
		ArrayList<Status> list = new ArrayList<Status>();
		JSONArray ja = new JSONArray(res);
		int ji = ja.length();
		for(int i = 0;i<ji;i++){
			list.add((Status)new StatusImpl(ja.getJSONObject(i)));
		}
		return list;
	}

	private ArrayList<Ids> toIds(String res) throws Exception{
		ArrayList<Ids> list = new ArrayList<Ids>();
		JSONArray ja = new JSONArray(res);
		int ji = ja.length();
		for(int i = 0;i<ji;i++){
			list.add((Ids)new IdsImpl(ja.getJSONObject(i)));
		}
		return list;
	}

	private ArrayList<User> toUser(String res) throws Exception{
		ArrayList<User> list = new ArrayList<User>();
		JSONArray ja = new JSONArray(res);
		int ji = ja.length();
		for(int i = 0;i<ji;i++){
			list.add((User)new UserImpl(ja.getJSONObject(i)));
		}
		return list;
	}

	private static final String BASE = "http://aclog.koba789.com";
	private static final String SHOW = "/api/tweets/show.json";
	private static final String BEST = "/api/tweets/best.json";
	private static final String FAVORITED = "/api/tweets/favorited.json";
	private static final String RETWEETED = "/api/tweets/retweeted.json";
	private static final String RECENT = "/api/tweets/recent.json";
	private static final String TL = "/api/tweets/timeline.json";
	private static final String DIS = "/api/tweets/discoveries.json";
	private static final String FAV = "/api/tweets/favorites.json";
	private static final String RT = "/api/tweets/retweets.json";
	private static final String DISBY = "/api/tweets/discovered_by.json";
	private static final String STATS = "/api/users/stats.json";
	private static final String USERDISBY = "/api/users/discovered_by.json";
	private static final String USERDISUSER = "/api/users/discovered_users.json";
	private static final String SCREEN = "/api/users/screen_name.json";

	/**
	 * @param tweetId
	 *            Twitter's Tweet ID
	 * 
	 * */
	public Status show(long tweetId) throws Exception{
		String res = get(toUrl(BASE+SHOW,new Paging(tweetId)));
		return (Status)new StatusImpl(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Status> getBest(Paging p) throws Exception{
		String res = get(toUrl(BASE+BEST,p));
		return toStatus(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Status> getFavorited(Paging p) throws Exception{
		String res = get(toUrl(BASE+FAVORITED,p));
		return toStatus(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Status> getRetweeted(Paging p) throws Exception{
		String res = get(toUrl(BASE+RETWEETED,p));
		return toStatus(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Status> getRecent(Paging p) throws Exception{
		String res = get(toUrl(BASE+RECENT,p));
		return toStatus(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Status> getTimeline(Paging p) throws Exception{
		String res = get(toUrl(BASE+TL,p));
		return toStatus(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Status> getDiscoveries(Paging p) throws Exception{
		String res = get(toUrl(BASE+DIS,p));
		return toStatus(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Status> getFavorites(Paging p) throws Exception{
		String res = get(toUrl(BASE+FAV,p));
		return toStatus(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Status> getRetweets(Paging p) throws Exception{
		String res = get(toUrl(BASE+RT,p));
		return toStatus(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Status> getDiscoveredBy(Paging p) throws Exception{
		String res = get(toUrl(BASE+DISBY,p));
		return toStatus(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public UserStats stats(Paging p) throws Exception{
		String res = get(toUrl(BASE+STATS,p));
		return (UserStats)new UserStatsImpl(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Ids> getUserDiscoveredBy(Paging p) throws Exception{
		String res = get(toUrl(BASE+USERDISBY,p));
		return toIds(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Ids> getUserDiscoveredUsers(Paging p) throws Exception{
		String res = get(toUrl(BASE+USERDISUSER,p));
		return toIds(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<User> getScreenName(ArrayList<Long> ids) throws Exception{
		String res = get(toUrl(BASE+SCREEN,ids));
		return toUser(res);
	}
}