package info.re4k.asfc.aclog;

public class APIKey{

	private String cok;
	private String cos;
	private String tok;
	private String tos;

	/**
	 * 
	 * @param cok ConsumerKey
	 * @param cos ConsumerSecret
	 * @param tok AccessToken
	 * @param tos AccessTokenSecret
	 */
	public APIKey(String cok,String cos,String tok,String tos){
		this.cok = cok;
		this.cos = cos;
		this.tok = tok;
		this.tos = tos;
	}

	public String getConsumerKey(){
		return cok;
	}

	public String getConsumerSecret(){
		return cos;
	}

	public String getAccessToken(){
		return tok;
	}

	public String getAccessTokenSecret(){
		return tos;
	}
}
