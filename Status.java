package info.re4k.asfc.aclog;

/**
 * Status data
 * */
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
	 * @see User
	 * */
	long[] getFavoritesUserId();

	/**
	 * @return aclog know Retweets User
	 * @see User
	 * */
	long[] getRetweetsUserId();
}