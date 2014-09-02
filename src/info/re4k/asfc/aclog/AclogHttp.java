package info.re4k.asfc.aclog;

import info.re4k.asfc.aclog.base64.Base64;
import info.re4k.asfc.aclog.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class AclogHttp{
	protected APIKey key;
	protected AclogConfig config;
	private static boolean isLog = false;
	private static final String TWITTER_VERIFY_CREDENTIALS_JSON_V1_1 = "https://api.twitter.com/1.1/account/verify_credentials.json";

	public AclogHttp(AclogConfig config){
		this.config=config;
	}

	public AclogHttp(APIKey key,AclogConfig config){
		this.key = key;
		this.config=config;
	}

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

	protected String createAuthorization() throws Exception{
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

	protected String get(String url) throws Exception{
		URL url2 = new URL(url);
		HttpURLConnection http = (HttpURLConnection)url2.openConnection();
		http.setRequestMethod("GET");
		http.setConnectTimeout(this.config.connectTimeOut);
		http.setReadTimeout(this.config.resdTimeout);
		http.setInstanceFollowRedirects(true);
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
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try{
			http.connect();
			if(http.getResponseCode()!=HttpURLConnection.HTTP_OK){
				throw new Exception(http.getResponseCode()+" : "+http.getResponseMessage());
			}
			logHeader(http);
			reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
			String line = null;
			while(true){
				line = reader.readLine();
				if(line==null){
					sb.deleteCharAt(sb.length()-1);
					break;
				}
				sb.append(line).append('\n');
			}
		}finally{
			http.disconnect();
			if(reader!=null){
				reader.close();
			}
		}
		String res = sb.toString();
		try{
			JSONObject json = new JSONObject(res);
			if(!json.isNull("error")){
				throw new Exception(json.getJSONObject("error").getString("message"));
			}
		}catch(Exception e){}
		return res;
	}

	private void logHeader(HttpURLConnection http){
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
	}
}
