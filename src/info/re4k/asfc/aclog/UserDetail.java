package info.re4k.asfc.aclog;

public interface UserDetail{
	/**
	 * @return aclog know User
	 * */
	long getUserId();

	/**
	 * @return aclog know Favorites count
	 * */
	int getFavoritesCount();

	/**
	 * @return aclog know Retweets count
	 * */
	int getRetweetsCount();
}
