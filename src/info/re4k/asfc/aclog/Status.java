package info.re4k.asfc.aclog;

public interface Status{

	/**
	 * @return Twitter's Tweet ID
	 * */
	long getId();

	/**
	 * @return aclog know User
	 * */
	long getUserId();

	/**
	 * @return aclog know Favorites Count
	 * */
	int getFavoritesCount();

	/**
	 * @return aclog know Retweets Count
	 * */
	int getRetweetsCount();

	/**
	 * @return aclog know Favorites User
	 * */
	long[] getFavoritesUserId();

	/**
	 * @return aclog know Retweets User
	 * */
	long[] getRetweetsUserId();
}