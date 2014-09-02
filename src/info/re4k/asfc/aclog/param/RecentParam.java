package info.re4k.asfc.aclog.param;

public class RecentParam extends PageParam{

	private String recent = null;

	public RecentParam(long user_id){
		super(user_id);
	}

	public RecentParam(String screen_name){
		super(screen_name);
	}

	public void setRecentDate(int d){
		recent = d+"d";
	}

	public void setRecentMonth(int m){
		recent = m+"m";
	}

	public void setRecentWeek(int w){
		recent = w+"w";
	}

	public void setRecentYear(int y){
		recent = y+"y";
	}

	@Override
	public StringBuilder toParam(){
		StringBuilder sb = super.toParam();
		if(recent!=null){
			sb.append("&recent=");
			sb.append(recent);
		}
		return sb;
	}

}
