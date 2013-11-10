package info.re4k.asfc.aclog;

/**
 * UserInfo data
 * */
public interface UserStats{

	/**
	 * @return aclog know User
	 * */
	long getUserId();

	/**
	 * @return aclog know Favorited count
	 * */
	int getFavoritedCount();

	/**
	 * @return aclog know Retweeted count
	 * */
	int getRetweetedCount();
}