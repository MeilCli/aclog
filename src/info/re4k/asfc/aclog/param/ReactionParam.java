package info.re4k.asfc.aclog.param;

public class ReactionParam extends PageParam{
	private int reactions = -1;

	public ReactionParam(long user_id){
		super(user_id);
	}

	public ReactionParam(String screen_name){
		super(screen_name);
	}

	public int getReactions(){
		return reactions;
	}

	public void setReactions(int reactions){
		this.reactions = reactions;
	}

	@Override
	public StringBuilder toParam(){
		StringBuilder sb = super.toParam();
		if(reactions!=-1){
			sb.append("&reactions=");
			sb.append(reactions);
		}
		return sb;
	}
}
