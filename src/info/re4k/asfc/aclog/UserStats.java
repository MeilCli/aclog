package info.re4k.asfc.aclog;

public interface UserStats{

	/**
	 * @return aclog know User
	 * */
	long getUserId();

	/**
	 * @return aclog know Favorited count and Retweeted count
	 * */
	long getReactionsCount();

}