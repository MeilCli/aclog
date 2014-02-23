package info.re4k.asfc.aclog.param;

public class TimeParam extends ReactionParam{

	private long since_id = -1;
	private long max_id = -1;

	public TimeParam(long user_id){
		super(user_id);
	}

	public TimeParam(String screen_name){
		super(screen_name);
	}

	public long getSinceId(){
		return since_id;
	}

	public void setSinceId(long since_id){
		this.since_id = since_id;
	}

	public long getMaxId(){
		return max_id;
	}

	public void setMaxId(long max_id){
		this.max_id = max_id;
	}

	@Override
	public StringBuilder toParam(){
		StringBuilder sb = super.toParam();
		if(since_id!=-1){
			sb.append("&since_id=");
			sb.append(since_id);
		}
		if(max_id!=-1){
			sb.append("&max_id=");
			sb.append(max_id);
		}
		return sb;
	}
}
