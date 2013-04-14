package com.fc2.blog.uameildi401.asfc4.aclog;

/**
 * Paging data
 * */

public class Paging{
	protected int count;
	protected int page = 1;
	protected int limit = 10;
	protected long tweet_id;
	protected long max_id;
	protected String screenName;

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
	 * @param screenName
	 *            Twitter's User screenName
	 * @param max_id
	 *            alike Twitter's max_id
	 * */
	public Paging(String screenName,long max_id){
		this.screenName = screenName;
		this.max_id = max_id;
	}

	/**
	 * @param screenName
	 *            Twitter's User screenName
	 * @param max_id
	 *            alike Twitter's max_id
	 * @param count
	 *            aclog's count(1-100)
	 * */
	public Paging(String screenName,long max_id,int count){
		this.screenName = screenName;
		this.max_id = max_id;
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
	 * @param limit
	 *            limit data of User Object
	 * */
	public void setLimit(int limit){
		this.limit = limit;
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
	 * @param screenName
	 *            Twitter's User screenName
	 * */
	public void setScreenName(String screenName){
		this.screenName = screenName;
	}
}