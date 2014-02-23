package info.re4k.asfc.aclog.param;

public class SourceParam extends ReactionParam{
	private long source_user_id = -1;
	private String source_screen_name;

	public SourceParam(long user_id,long source_user_id){
		super(user_id);
		this.source_user_id = source_user_id;
	}

	public SourceParam(String screen_name,long source_user_id){
		super(screen_name);
		this.source_user_id = source_user_id;
	}

	public SourceParam(long user_id,String source_screen_name){
		super(user_id);
		this.source_screen_name = source_screen_name;
	}

	public SourceParam(String screen_name,String source_screen_name){
		super(screen_name);
		this.source_screen_name = source_screen_name;
	}

	public long getSourceUserId(){
		return source_user_id;
	}

	public String getSourceScreenName(){
		return source_screen_name;
	}

	@Override
	public StringBuilder toParam(){
		StringBuilder sb = super.toParam();
		if(source_user_id!=-1){
			sb.append("&source_user_id=");
			sb.append(source_user_id);
		}
		if(source_screen_name!=null){
			sb.append("&source_screen_name=");
			sb.append(source_screen_name);
		}
		return sb;
	}
}
