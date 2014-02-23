package info.re4k.asfc.aclog.param;

public class PageParam implements Param{
	private long user_id = -1;
	private String screen_name;
	private int count = -1;
	private int page = -1;

	public PageParam(long user_id){
		this.user_id = user_id;
	}

	public PageParam(String screen_name){
		this.screen_name = screen_name;
	}

	public long getId(){
		return user_id;
	}

	public String getScreenName(){
		return screen_name;
	}

	public int getCount(){
		return count;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getPage(){
		return page;
	}

	public void setPage(int page){
		this.page = page;
	}

	@Override
	public StringBuilder toParam(){
		StringBuilder sb = new StringBuilder();
		sb.append("?");
		if(user_id!=-1){
			sb.append("&user_id=");
			sb.append(user_id);
		}
		if(screen_name!=null){
			sb.append("&screen_name=");
			sb.append(screen_name);
		}
		if(count!=-1){
			sb.append("&count=");
			sb.append(count);
		}
		if(page!=-1){
			sb.append("&page=");
			sb.append(page);
		}
		return sb;
	}
}
