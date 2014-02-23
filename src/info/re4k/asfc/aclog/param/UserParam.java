package info.re4k.asfc.aclog.param;

public class UserParam implements Param{
	private long id = -1;
	private String screen_name;

	public UserParam(long user_id){
		this.id = user_id;
	}

	public UserParam(String screen_name){
		this.screen_name = screen_name;
	}

	public long getId(){
		return id;
	}

	public String getScreenName(){
		return screen_name;
	}

	@Override
	public StringBuilder toParam(){
		StringBuilder sb = new StringBuilder();
		sb.append("?");
		if(id!=-1){
			sb.append("&id=");
			sb.append(id);
		}
		if(screen_name!=null){
			sb.append("&screen_name=");
			sb.append(screen_name);
		}
		return sb;
	}
}
