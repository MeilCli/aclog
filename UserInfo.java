package com.fc2.blog.uameildi401.asfc4.aclog;

/**
 * UserInfo data
 * */
public interface UserInfo{
	
	/**
	 * @return aclog know User
	 * @see User
	 * */
	User getUser();
	
	/**
	 * @return aclog know Favorited count
	 * */
	int getFavoritedCount();
	
	/**
	 * @return aclog know Retweeted count
	 * */
	int getRetweetedCount();
}