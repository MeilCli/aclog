package info.re4k.asfc.aclog;

/**
 * Ids data
 * */
public interface Ids{

	/**
	 * @return aclog know User
	 * @see User
	 * */
	long getUserId();

	/**
	 * @return aclog know count
	 * */
	int getFavoritesCount();

	/**
	 * @return aclog know count
	 * */
	int getRetweetsCount();
}