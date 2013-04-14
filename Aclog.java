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
		url += "?include_user=true";
		url += "&limit="+p.limit;
		url += "&page="+p.page;
		if(p.screenName!=null)
			url += "&screen_name="+p.screenName;
		if(p.count!=0)
			url += "&count="+p.count;
		if(p.tweet_id!=0)
			url += "&id="+p.tweet_id;
		if(p.max_id!=0)
			url += "&max_id="+p.max_id;
		return url;
	}

	private String get(String url,Paging p) throws Exception{
		URL url2 = new URL(toUrl(url,p));
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

	private static final String BASE = "http://aclog.koba789.com";
	private static final String SHOW = "/i/show.json";
	private static final String BEST = "/users/best.json";
	private static final String TL = "/users/timeline.json";
	private static final String DIS = "/users/discovered.json";
	private static final String FAVBY = "/users/favorited_by.json";
	private static final String RTBY = "/users/retweeted_by.json";
	private static final String GIVFAV = "/users/given_favorites_to.json";
	private static final String GIVRT = "/users/given_retweets_to.json";
	private static final String INFO = "/users/info.json";

	/**
	 * @param tweetId
	 *            Twitter's Tweet ID
	 * 
	 * */
	public Status show(long tweetId) throws Exception{
		String res = get(BASE+SHOW,new Paging(tweetId));
		return (Status)new StatusImpl(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * @param page
	 *            aclog's page(1,2,3,...)
	 * */
	public ArrayList<Status> getBest(String screenName,int page) throws Exception{
		String res = get(BASE+BEST,new Paging(screenName,page));
		return toStatus(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Status> getBest(Paging p) throws Exception{
		String res = get(BASE+BEST,p);
		return toStatus(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * @param page
	 *            aclog's page(1,2,3,...)
	 * */
	public ArrayList<Status> getTimeline(String screenName,int page) throws Exception{
		String res = get(BASE+TL,new Paging(screenName,page));
		return toStatus(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Status> getTimeline(Paging p) throws Exception{
		String res = get(BASE+TL,p);
		return toStatus(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * @param page
	 *            aclog's page(1,2,3,...)
	 * */
	public ArrayList<Status> getDiscovered(String screenName,int page) throws Exception{
		String res = get(BASE+DIS,new Paging(screenName,page));
		return toStatus(res);
	}

	/**
	 * @param p
	 *            Paging
	 * */
	public ArrayList<Status> getDiscovered(Paging p) throws Exception{
		String res = get(BASE+DIS,p);
		return toStatus(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * */
	public ArrayList<Ids> getFavoritedBy(String screenName) throws Exception{
		String res = get(BASE+FAVBY,new Paging(screenName));
		return toIds(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * */
	public ArrayList<Ids> getRetweetedBy(String screenName) throws Exception{
		String res = get(BASE+RTBY,new Paging(screenName));
		return toIds(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * */
	public ArrayList<Ids> getFavoritesTo(String screenName) throws Exception{
		String res = get(BASE+GIVFAV,new Paging(screenName));
		return toIds(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * */
	public ArrayList<Ids> getRetweetsTo(String screenName) throws Exception{
		String res = get(BASE+GIVRT,new Paging(screenName));
		return toIds(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * */
	public UserInfo info(String screenName) throws Exception{
		String res = get(BASE+INFO,new Paging(screenName));
		return (UserInfo)new UserInfoImpl(res);
	}
}