package info.re4k.asfc.aclog;

import info.re4k.asfc.aclog.base64.Base64;
import info.re4k.asfc.aclog.impl.StatusImpl;
import info.re4k.asfc.aclog.impl.UserDetailImpl;
import info.re4k.asfc.aclog.impl.UserImpl;
import info.re4k.asfc.aclog.impl.UserStatsImpl;
import info.re4k.asfc.aclog.json.JSONArray;
import info.re4k.asfc.aclog.json.JSONObject;
import info.re4k.asfc.aclog.param.IdsParam;
import info.re4k.asfc.aclog.param.PageParam;
import info.re4k.asfc.aclog.param.ReactionParam;
import info.re4k.asfc.aclog.param.SourceParam;
import info.re4k.asfc.aclog.param.TimeParam;
import info.re4k.asfc.aclog.param.UserParam;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/*
 * Production environment is JavaSE-1.6
 * */
public class Aclog{

	private APIKey key;
	private static boolean isLog = false;

	public Aclog(){}

	public Aclog(APIKey key){
		this.key = key;
	}

	private static final String TWITTER_VERIFY_CREDENTIALS_JSON_V1_1 = "https://api.twitter.com/1.1/account/verify_credentials.json";

	private String urlEncode(String string){
		try{
			return URLEncoder.encode(string,"UTF-8");
		}catch(UnsupportedEncodingException e){
			throw new RuntimeException(e);
		}
	}

	private String makeParam(SortedMap<String,String> params,boolean signature){
		StringBuilder sb = new StringBuilder();
		for(Entry<String,String> param:params.entrySet()){
			if(sb.length()>0){
				if(signature==true){
					sb.append('&');
				}else{
					sb.append(',');
				}
			}
			sb.append(param.getKey());
			sb.append('=');
			if(signature==false){
				sb.append('"');
			}
			if(signature==true){
				sb.append(param.getValue());
			}else{
				sb.append(urlEncode(param.getValue()));
			}
			if(signature==false){
				sb.append('"');
			}
		}
		return sb.toString();
	}

	private String createAuthorization() throws Exception{
		SortedMap<String,String> params = new TreeMap<String,String>();
		params.put("oauth_consumer_key",key.getConsumerKey());
		params.put("oauth_signature_method","HMAC-SHA1");
		params.put("oauth_token",key.getAccessToken());
		params.put("oauth_version","1.0");
		params.put("oauth_timestamp",String.valueOf((long)(System.currentTimeMillis()/1000)));
		params.put("oauth_nonce",String.valueOf(Math.random()));

		String text = "GET&"+urlEncode(TWITTER_VERIFY_CREDENTIALS_JSON_V1_1)+"&"+urlEncode(makeParam(params,true));
		if(isLog==true){
			System.out.println("signature text");
			System.out.println(text);
		}
		String keyText = urlEncode(key.getConsumerSecret())+"&"+urlEncode(key.getAccessTokenSecret());
		SecretKeySpec signingKey = new SecretKeySpec(keyText.getBytes(),"HmacSHA1");
		Mac mac = Mac.getInstance(signingKey.getAlgorithm());
		mac.init(signingKey);
		byte[] binary = mac.doFinal(text.getBytes());
		String signature = Base64.encodeBase64String(binary);

		params.put("oauth_signature",signature);

		StringBuilder sb = new StringBuilder();
		sb.append("OAuth realm=");
		sb.append('"');
		sb.append("http://api.twitter.com/");
		sb.append('"');
		sb.append(',');
		sb.append(makeParam(params,false));

		return sb.toString();
	}

	private String get(String url) throws Exception{
		URL url2 = new URL(url);
		HttpURLConnection http = (HttpURLConnection)url2.openConnection();
		http.setRequestMethod("GET");

		if(key!=null){
			try{
				http.setRequestProperty("X-Auth-Service-Provider",TWITTER_VERIFY_CREDENTIALS_JSON_V1_1);
				http.setRequestProperty("X-Verify-Credentials-Authorization",createAuthorization());
				if(isLog==true){
					System.out.println("add OauthEcho");
					System.out.println(createAuthorization());
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		http.connect();
		if(isLog==true){
			Map<String,List<String>> headers = http.getHeaderFields();
			Iterator<String> headerIt = headers.keySet().iterator();
			String header = null;
			while(headerIt.hasNext()){
				String headerKey = (String)headerIt.next();
				header += headerKey+"："+headers.get(headerKey)+"\r\n";
			}
			System.out.println(header);
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
		StringBuilder sb = new StringBuilder();
		while(true){
			String line = reader.readLine();
			if(line==null){
				break;
			}
			sb.append(line).append('\n');
		}
		http.disconnect();
		reader.close();
		String res = sb.toString();
		try{
			JSONObject json = new JSONObject(res);
			if(!json.isNull("error")){
				throw new Exception(json.getJSONObject("error").getString("message"));
			}
		}catch(Exception e){}
		return res;
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

	private static final String BASE = "http://aclog.koba789.com";
	private static final String SHOW = "/api/tweets/show.json";
	private static final String LOOK = "/api/tweets/lookup.json";
	private static final String BEST = "/api/tweets/user_best.json";
	private static final String TL = "/api/tweets/user_timeline.json";
	private static final String DIS = "/api/tweets/user_discoveries.json";
	private static final String DISBY = "/api/tweets/user_discovered_by.json";
	private static final String STATS = "/api/users/stats.json";
	private static final String USERDISBY = "/api/users/discovered_by.json";
	private static final String USERDISUSER = "/api/users/discovered_users.json";
	private static final String SCREEN = "/api/users/screen_name.json";

	public Status show(long tweetId) throws Exception{
		String res = get(BASE+SHOW+"?id="+tweetId);
		return new StatusImpl(res);
	}

	public ArrayList<Status> lookup(IdsParam p) throws Exception{
		String res = get(p.toParam().insert(0,BASE+LOOK).toString());
		return toStatus(res);
	}

	public ArrayList<Status> getBest(PageParam p) throws Exception{
		String res = get(p.toParam().insert(0,BASE+BEST).toString());
		return toStatus(res);
	}

	public ArrayList<Status> getTimeline(TimeParam p) throws Exception{
		String res = get(p.toParam().insert(0,BASE+TL).toString());
		return toStatus(res);
	}

	public ArrayList<Status> getDiscoveries(ReactionParam p) throws Exception{
		String res = get(p.toParam().insert(0,BASE+DIS).toString());
		return toStatus(res);
	}

	public ArrayList<Status> getDiscoveredBy(SourceParam p) throws Exception{
		String res = get(p.toParam().insert(0,BASE+DISBY).toString());
		return toStatus(res);
	}

	public UserStats stats(UserParam p) throws Exception{
		String res = get(p.toParam().insert(0,BASE+STATS).toString());
		return new UserStatsImpl(res);
	}

	public ArrayList<UserDetail> getUserDiscoveredBy(UserParam p) throws Exception{
		String res = get(p.toParam().insert(0,BASE+USERDISBY).toString());
		return toUserDetail(res);
	}

	public ArrayList<UserDetail> getUserDiscoveredUsers(UserParam p) throws Exception{
		String res = get(p.toParam().insert(0,BASE+USERDISUSER).toString());
		return toUserDetail(res);
	}

	public ArrayList<User> getScreenName(IdsParam p) throws Exception{
		String res = get(p.toParam().insert(0,BASE+SCREEN).toString());
		return toUser(res);
	}
}