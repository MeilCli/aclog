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

	private String get(String url,String screenName,int page,int count) throws Exception{
		URL url2 = new URL(url+"?screen_name="+screenName+"&include_user=true"+(page==0?"":"&page="+page)+(count==0?"":"&count="+count));
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

	private String get(String url,long id) throws Exception{
		URL url2 = new URL(url+"?id="+id+"&include_user=true");
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
		String res = get(BASE+SHOW,tweetId);
		return (Status)new StatusImpl(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * @param page
	 *            aclog's page(1,2,3,...)
	 * */
	public ArrayList<Status> getBest(String screenName,int page) throws Exception{
		String res = get(BASE+BEST,screenName,page,0);
		return toStatus(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * @param page
	 *            aclog's page(1,2,3,...)
	 * @param count
	 *            aclog's count(1-100)
	 * */
	public ArrayList<Status> getBest(String screenName,int page,int count) throws Exception{
		String res = get(BASE+BEST,screenName,page,count);
		return toStatus(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * @param page
	 *            aclog's page(1,2,3,...)
	 * */
	public ArrayList<Status> getTimeline(String screenName,int page) throws Exception{
		String res = get(BASE+TL,screenName,page,0);
		return toStatus(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * @param page
	 *            aclog's page(1,2,3,...)
	 * @param count
	 *            aclog's count(1-100)
	 * */
	public ArrayList<Status> getTimeline(String screenName,int page,int count) throws Exception{
		String res = get(BASE+TL,screenName,page,count);
		return toStatus(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * @param page
	 *            aclog's page(1,2,3,...)
	 * */
	public ArrayList<Status> getDiscovered(String screenName,int page) throws Exception{
		String res = get(BASE+DIS,screenName,page,0);
		return toStatus(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * @param page
	 *            aclog's page(1,2,3,...)
	 * @param count
	 *            aclog's count(1-100)
	 * */
	public ArrayList<Status> getDiscovered(String screenName,int page,int count) throws Exception{
		String res = get(BASE+DIS,screenName,page,count);
		return toStatus(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * */
	public ArrayList<Ids> getFavoritedBy(String screenName) throws Exception{
		String res = get(BASE+FAVBY,screenName,0,0);
		return toIds(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * */
	public ArrayList<Ids> getRetweetedBy(String screenName) throws Exception{
		String res = get(BASE+RTBY,screenName,0,0);
		return toIds(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * */
	public ArrayList<Ids> getFavoritesTo(String screenName) throws Exception{
		String res = get(BASE+GIVFAV,screenName,0,0);
		return toIds(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * */
	public ArrayList<Ids> getRetweetsTo(String screenName) throws Exception{
		String res = get(BASE+GIVRT,screenName,0,0);
		return toIds(res);
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * */
	public UserInfo info(String screenName) throws Exception{
		String res = get(BASE+INFO,screenName,1,0);
		return (UserInfo)new UserInfoImpl(res);
	}
}