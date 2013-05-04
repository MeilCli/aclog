package com.fc2.blog.uameildi401.asfc4.aclog;

/**
 * Paging data
 * */
public class Paging{
	protected int count;
	protected int page = 1;
	protected long tweet_id;
	protected long max_id;
	protected long since_id;
	protected long userId;
	protected long userId_b;
	protected String screenName;
	protected String screenName_b;

	public Paging(){}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * */
	public Paging(String screenName){
		this.screenName = screenName;
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * @param page
	 *            aclog's page(1,2,3,...)
	 * */
	public Paging(String screenName,int page){
		this.screenName = screenName;
		this.page = page;
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * @param page
	 *            aclog's page(1,2,3,...)
	 * @param count
	 *            aclog's count(1-100)
	 * */
	public Paging(String screenName,int page,int count){
		this.screenName = screenName;
		this.page = page;
		this.count = count;
	}

	/**
	 * @param tweet_id
	 *            Twitter's Tweet ID
	 * */
	public Paging(long tweet_id){
		this.tweet_id = tweet_id;
	}

	/**
	 * @param count
	 *            aclog's count(1-100)
	 * */
	public void setCount(int count){
		this.count = count;
	}

	/**
	 * @param page
	 *            aclog's page(1,2,3,...)
	 * */
	public void setPage(int page){
		this.page = page;
	}

	/**
	 * @param tweet_id
	 *            Twitter's Tweet ID
	 * */
	public void setTweet_id(long tweet_id){
		this.tweet_id = tweet_id;
	}

	/**
	 * @param max_id
	 *            alike Twitter's max_id
	 * */
	public void setMax_id(long max_id){
		this.max_id = max_id;
	}
	
	/**
	 * @param since_id
	 *            alike Twitter's since_id
	 * */
	public void setSince_id(long since_id){
		this.since_id = since_id;
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * */
	public void setScreenName(String screenName){
		this.screenName = screenName;
	}
	
	/**
	 * @param screenName_b
	 *            Twitter's User screenName
	 * */
	public void setScreenName_b(String screenName_b){
		this.screenName_b = screenName_b;
	}
	
	/**
	 * @param userId
	 *            Twitter's User ID
	 * */
	public void setUserId(long userId){
		this.userId = userId;
	}
	
	/**
	 * @param userId_b
	 *            Twitter's User ID
	 * */
	public void setUserId_b(long userId_b){
		this.userId_b = userId_b;
	}
}