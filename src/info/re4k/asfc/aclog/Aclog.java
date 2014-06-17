package info.re4k.asfc.aclog;

import info.re4k.asfc.aclog.impl.StatusImpl;
import info.re4k.asfc.aclog.impl.UserDetailImpl;
import info.re4k.asfc.aclog.impl.UserImpl;
import info.re4k.asfc.aclog.impl.UserStatsImpl;
import info.re4k.asfc.aclog.json.JSONArray;
import info.re4k.asfc.aclog.param.IdsParam;
import info.re4k.asfc.aclog.param.PageParam;
import info.re4k.asfc.aclog.param.ReactionParam;
import info.re4k.asfc.aclog.param.SourceParam;
import info.re4k.asfc.aclog.param.TimeParam;
import info.re4k.asfc.aclog.param.UserParam;
import java.util.ArrayList;

/*
 * Production environment is JavaSE-1.6
 * */
public class Aclog extends AclogHttp implements AclogConstant{

	public Aclog(){
		super();
	}

	public Aclog(APIKey key){
		super(key);
	}

	public Status show(long tweetId) throws Exception{
		String res = get(show+"?id="+tweetId);
		return new StatusImpl(res);
	}

	public ArrayList<Status> lookup(IdsParam p) throws Exception{
		String res = get(p.toParam().insert(0,lookup).toString());
		return toStatus(res);
	}

	public ArrayList<Status> getBest(PageParam p) throws Exception{
		String res = get(p.toParam().insert(0,user_best).toString());
		return toStatus(res);
	}

	public ArrayList<Status> getTimeline(TimeParam p) throws Exception{
		String res = get(p.toParam().insert(0,user_timeline).toString());
		return toStatus(res);
	}

	public ArrayList<Status> getFavorites(ReactionParam p) throws Exception{
		String res = get(p.toParam().insert(0,user_favorites).toString());
		return toStatus(res);
	}

	public ArrayList<Status> getFavoritedBy(SourceParam p) throws Exception{
		String res = get(p.toParam().insert(0,user_favorited_by).toString());
		return toStatus(res);
	}

	public UserStats stats(UserParam p) throws Exception{
		String res = get(p.toParam().insert(0,stats).toString());
		return new UserStatsImpl(res);
	}

	public ArrayList<UserDetail> getUserDiscoveredBy(UserParam p) throws Exception{
		String res = get(p.toParam().insert(0,discovered_by).toString());
		return toUserDetail(res);
	}

	public ArrayList<UserDetail> getUserDiscoveredUsers(UserParam p) throws Exception{
		String res = get(p.toParam().insert(0,discovered_users).toString());
		return toUserDetail(res);
	}

	public ArrayList<User> getScreenName(IdsParam p) throws Exception{
		String res = get(p.toParam().insert(0,screen_name).toString());
		return toUser(res);
	}

	private ArrayList<Status> toStatus(String res) throws Exception{
		ArrayList<Status> list = new ArrayList<Status>();
		JSONArray ja = new JSONArray(res);
		int ji = ja.length();
		for(int i = 0;i<ji;i++){
			list.add(new StatusImpl(ja.getJSONObject(i)));
		}
		return list;
	}

	private ArrayList<UserDetail> toUserDetail(String res) throws Exception{
		ArrayList<UserDetail> list = new ArrayList<UserDetail>();
		JSONArray ja = new JSONArray(res);
		int ji = ja.length();
		for(int i = 0;i<ji;i++){
			list.add(new UserDetailImpl(ja.getJSONObject(i)));
		}
		return list;
	}

	private ArrayList<User> toUser(String res) throws Exception{
		ArrayList<User> list = new ArrayList<User>();
		JSONArray ja = new JSONArray(res);
		int ji = ja.length();
		for(int i = 0;i<ji;i++){
			list.add(new UserImpl(ja.getJSONObject(i)));
		}
		return list;
	}
}