package info.re4k.asfc.aclog;

public interface AclogConstant{
	public static final String base = "http://aclog.koba789.com";

	public static final String show = base+"/api/tweets/show.json";
	public static final String lookup = base+"/api/tweets/lookup.json";
	public static final String user_best = base+"/api/tweets/user_best.json";
	public static final String user_timeline = base+"/api/tweets/user_timeline.json";
	public static final String user_discoveries = base+"/api/tweets/user_discoveries.json";
	public static final String user_discovered_by = base+"/api/tweets/user_discovered_by.json";
	public static final String stats = base+"/api/users/stats.json";
	public static final String discovered_by = base+"/api/users/discovered_by.json";
	public static final String discovered_users = base+"/api/users/discovered_users.json";
	public static final String screen_name = base+"/api/users/screen_name.json";
}
