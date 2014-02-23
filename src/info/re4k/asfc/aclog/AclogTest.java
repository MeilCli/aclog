package info.re4k.asfc.aclog;

import java.util.ArrayList;
import info.re4k.asfc.aclog.param.IdsParam;
import info.re4k.asfc.aclog.param.PageParam;
import info.re4k.asfc.aclog.param.ReactionParam;
import info.re4k.asfc.aclog.param.SourceParam;
import info.re4k.asfc.aclog.param.TimeParam;
import info.re4k.asfc.aclog.param.UserParam;

public class AclogTest{

	private static boolean isLog = false;
	private static String testUserScrrenName1 = "ta_ka_na_M";
	private static String testUserScrrenName2 = "cn";
	private static long testUserId1 = 262154677;// ta_ka_na_M
	private static long testUserId2 = 280414022;// cn
	private static long testTweetId1 = 43341783446466560L;
	private static long testTweetId2 = 50220624609685505L;
	private static APIKey key;
	private static Aclog aclog = new Aclog(key);

	/**
	 * @param args
	 */
	public static void main(String[] args){
		stats();
		getScreenName();
		getUserDiscoveredBy();
		getUserDiscoveredUsers();
		getBest();
		getDiscoveries();
		getDiscoveredBy();
		getTimeline();
		lookup();
		show();
	}

	private static void show(){
		System.out.println("Aclog#show start");
		try{
			testStatus(aclog.show(testTweetId1));
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Aclog#show end");
	}

	private static void lookup(){
		System.out.println("Aclog#lookup start");
		IdsParam p1 = new IdsParam(testTweetId1);
		ArrayList<Long> list = new ArrayList<Long>();
		list.add(testTweetId1);
		list.add(testTweetId2);
		IdsParam p2 = new IdsParam(list);
		IdsParam[] p = {p1,p2};
		for(IdsParam u:p){
			if(isLog==true){
				System.out.print("IdsParam#toParam()==");
				System.out.println(u.toParam().toString());
			}
			try{
				testStatuses(aclog.lookup(u));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("Aclog#lookup end");
	}

	private static void getTimeline(){
		System.out.println("Aclog#getTimeline start");
		TimeParam p1 = new TimeParam(testUserScrrenName1);
		p1.setCount(10);
		p1.setPage(1);
		p1.setReactions(3);
		TimeParam p2 = new TimeParam(testUserId1);
		p2.setCount(10);
		p2.setPage(1);
		p2.setReactions(3);
		TimeParam[] p = {p1,p2};
		for(TimeParam u:p){
			if(isLog==true){
				System.out.print("TimeParam#toParam()==");
				System.out.println(u.toParam().toString());
			}
			try{
				testStatuses(aclog.getTimeline(u));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("Aclog#getTimeline end");
	}

	private static void getDiscoveredBy(){
		System.out.println("Aclog#getDiscoveredBy start");
		SourceParam p1 = new SourceParam(testUserScrrenName2,testUserId1);
		p1.setCount(10);
		p1.setPage(1);
		p1.setReactions(3);
		SourceParam p2 = new SourceParam(testUserId2,testUserScrrenName1);
		p2.setCount(10);
		p2.setPage(1);
		p2.setReactions(3);
		SourceParam[] p = {p1,p2};
		for(SourceParam u:p){
			if(isLog==true){
				System.out.print("SourceParam#toParam()==");
				System.out.println(u.toParam().toString());
			}
			try{
				testStatuses(aclog.getDiscoveredBy(u));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("Aclog#getDiscoveredBy end");
	}

	private static void getDiscoveries(){
		System.out.println("Aclog#getDiscoveries start");
		ReactionParam p1 = new ReactionParam(testUserScrrenName1);
		p1.setCount(10);
		p1.setPage(1);
		p1.setReactions(3);
		ReactionParam p2 = new ReactionParam(testUserId1);
		p2.setCount(10);
		p2.setPage(1);
		p2.setReactions(3);
		ReactionParam[] p = {p1,p2};
		for(ReactionParam u:p){
			if(isLog==true){
				System.out.print("ReactionParam#toParam()==");
				System.out.println(u.toParam().toString());
			}
			try{
				testStatuses(aclog.getDiscoveries(u));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("Aclog#getDiscoveries end");
	}

	private static void getBest(){
		System.out.println("Aclog#getBest start");
		PageParam p1 = new PageParam(testUserScrrenName1);
		p1.setCount(10);
		p1.setPage(1);
		PageParam p2 = new PageParam(testUserId1);
		p2.setCount(10);
		p2.setPage(1);
		PageParam[] p = {p1,p2};
		for(PageParam u:p){
			if(isLog==true){
				System.out.print("PageParam#toParam()==");
				System.out.println(u.toParam().toString());
			}
			try{
				testStatuses(aclog.getBest(u));
			}catch(Exception e){
				e.printStackTrace();				
			}
		}
		System.out.println("Aclog#getBest end");
	}

	private static void getUserDiscoveredUsers(){
		System.out.println("Aclog#getUserDiscoveredUsers start");
		UserParam p1 = new UserParam(testUserScrrenName1);
		UserParam p2 = new UserParam(testUserId1);
		UserParam[] p = {p1,p2};
		for(UserParam u:p){
			if(isLog==true){
				System.out.print("UserParam#toParam()==");
				System.out.println(u.toParam().toString());
			}
			try{
				testUserDetails(aclog.getUserDiscoveredUsers(u));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("Aclog#getUserDiscoveredUsers end");
	}

	private static void getUserDiscoveredBy(){
		System.out.println("Aclog#getUserDiscoveredBy start");
		UserParam p1 = new UserParam(testUserScrrenName1);
		UserParam p2 = new UserParam(testUserId1);
		UserParam[] p = {p1,p2};
		for(UserParam u:p){
			if(isLog==true){
				System.out.print("UserParam#toParam()==");
				System.out.println(u.toParam().toString());
			}
			try{
				testUserDetails(aclog.getUserDiscoveredBy(u));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("Aclog#getUserDiscoveredBy end");
	}

	private static void getScreenName(){
		System.out.println("Aclog#getScreenName start");
		IdsParam p1 = new IdsParam(testUserId1);
		ArrayList<Long> list = new ArrayList<Long>();
		list.add(testUserId1);
		list.add(testUserId2);
		IdsParam p2 = new IdsParam(list);
		IdsParam[] p = {p1,p2};
		for(IdsParam i:p){
			if(isLog==true){
				System.out.print("IdParam#toParam()==");
				System.out.println(i.toParam().toString());
			}
			try{
				for(User u:aclog.getScreenName(i)){
					testUser(u);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("Aclog#getScreenName end");
	}

	private static void stats(){
		System.out.println("Aclog#stats start");
		UserParam p1 = new UserParam(testUserScrrenName1);
		UserParam p2 = new UserParam(testUserId1);
		UserParam[] p = {p1,p2};
		for(UserParam u:p){
			if(isLog==true){
				System.out.print("UserParam#toParam()==");
				System.out.println(u.toParam().toString());
			}
			try{
				testUserStats(aclog.stats(u));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("Aclog#stats end");
	}

	private static void testStatus(Status status){
		if(status.getId()==-1){
			System.out.println("Status#getId()==-1");
		}else if(isLog==true){
			System.out.print("Status#getId()==");
			System.out.println(status.getId());
		}
		if(status.getUserId()==-1){
			System.out.println("Status#getUserId()==-1");
		}else if(isLog==true){
			System.out.print("Status#getUserId()==");
			System.out.println(status.getUserId());
		}
		if(status.getFavoritesCount()==-1){
			System.out.println("Status#getFavoritesCount()==-1");
		}else if(isLog==true){
			System.out.print("Status#getFavoritesCount()==");
			System.out.println(status.getFavoritesCount());
		}
		if(status.getRetweetsCount()==-1){
			System.out.println("Status#getRetweetsCount()==-1");
		}else if(isLog==true){
			System.out.print("Status#getRetweetsCount()==");
			System.out.println(status.getRetweetsCount());
		}
		if(status.getFavoritesUserId()==null){
			System.out.println("Status#getFavoritesUserId()==null");
		}else if(isLog==true){
			System.out.print("Status#getFavoritesUserId().length==");
			System.out.println(status.getFavoritesUserId().length);
		}
		if(status.getRetweetsUserId()==null){
			System.out.println("Status#getRetweetsUserId()==null");
		}else if(isLog==true){
			System.out.print("Status#getRetweetsUserId().length==");
			System.out.println(status.getRetweetsUserId().length);
		}
	}

	private static void testStatuses(ArrayList<Status> statuses){
		if(statuses.size()==0){
			System.out.println("ArrayList#size()==0");
		}
		for(int i = 0;i<statuses.size();i++){
			Status status = statuses.get(i);
			if(status.getId()==-1){
				System.out.println("Status#getId()==-1");
			}else if(isLog==true&&i==0){
				System.out.print("Status#getId()==");
				System.out.println(status.getId());
			}
			if(status.getUserId()==-1){
				System.out.println("Status#getUserId()==-1");
			}else if(isLog==true&&i==0){
				System.out.print("Status#getUserId()==");
				System.out.println(status.getUserId());
			}
			if(status.getFavoritesCount()==-1){
				System.out.println("Status#getFavoritesCount()==-1");
			}else if(isLog==true&&i==0){
				System.out.print("Status#getFavoritesCount()==");
				System.out.println(status.getFavoritesCount());
			}
			if(status.getRetweetsCount()==-1){
				System.out.println("Status#getRetweetsCount()==-1");
			}else if(isLog==true&&i==0){
				System.out.print("Status#getRetweetsCount()==");
				System.out.println(status.getRetweetsCount());
			}
			if(status.getFavoritesUserId()==null){
				System.out.println("Status#getFavoritesUserId()==null");
			}else if(isLog==true&&i==0){
				System.out.print("Status#getFavoritesUserId().length==");
				System.out.println(status.getFavoritesUserId().length);
			}
			if(status.getRetweetsUserId()==null){
				System.out.println("Status#getRetweetsUserId()==null");
			}else if(isLog==true&&i==0){
				System.out.print("Status#getRetweetsUserId().length==");
				System.out.println(status.getRetweetsUserId().length);
			}
		}
	}

	private static void testUserDetails(ArrayList<UserDetail> statuses){
		if(statuses.size()==0){
			System.out.println("ArrayList#size()==0");
		}
		for(int i = 0;i<statuses.size();i++){
			UserDetail status = statuses.get(i);
			if(status.getUserId()==-1){
				System.out.println("UserDetail#getUserId()==-1");
			}else if(isLog==true&&i==0){
				System.out.print("UserDetail#getUserId()==");
				System.out.println(status.getUserId());
			}
			if(status.getFavoritesCount()==-1){
				System.out.println("UserDetail#getFavoritesCount()==-1");
			}else if(isLog==true&&i==0){
				System.out.print("UserDetail#getFavoritesCount()==");
				System.out.println(status.getFavoritesCount());
			}
			if(status.getRetweetsCount()==-1){
				System.out.println("UserDetail#getRetweetsCount()==-1");
			}else if(isLog==true&&i==0){
				System.out.print("UserDetail#getRetweetsCount()==");
				System.out.println(status.getRetweetsCount());
			}
		}
	}

	private static void testUser(User status){
		if(status.getUserId()==-1){
			System.out.println("UserStats#getUserId()==-1");
		}else if(isLog==true){
			System.out.print("UserStats#getUserId()==");
			System.out.println(status.getUserId());
		}
		if(status.getScreenName()==null){
			System.out.println("UserStats#getScreenName()==null");
		}else if(isLog==true){
			System.out.print("UserStats#getScreenName()==");
			System.out.println(status.getScreenName());
		}
	}

	private static void testUserStats(UserStats status){
		if(status.getReactionsCount()==-1){
			System.out.println("UserStats#getReactionsCount()==-1");
		}else if(isLog==true){
			System.out.print("UserStats#getReactionsCount()==");
			System.out.println(status.getReactionsCount());
		}
		if(status.getUserId()==-1){
			System.out.println("UserStats#getUserId()==-1");
		}else if(isLog==true){
			System.out.print("UserStats#getUserId()==");
			System.out.println(status.getUserId());
		}
	}
}
