package info.re4k.asfc.aclog;

public interface UserStats{

	/**
	 * @return aclog know User
	 * */
	long getUserId();

	/**
	 * @return aclog know Favorited count and Retweeted count
	 * */
	int getReactionsCount();
	
	/**
	 * @return this user is registered in aclog
	 */
	boolean isRegistered();

}