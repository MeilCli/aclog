package info.re4k.asfc.aclog.param;

import java.util.List;

public class IdsParam implements Param{
	private long[] ids;

	public IdsParam(long id){
		this.ids = new long[1];
		this.ids[0] = id;
	}

	public IdsParam(long[] ids){
		this.ids = ids;
	}

	public IdsParam(List<Long> ids){
		this.ids = new long[ids.size()];
		for(int i = 0;i<ids.size();i++){
			this.ids[i] = ids.get(i);
		}
	}

	public long[] getIds(){
		return ids;
	}

	@Override
	public StringBuilder toParam(){
		StringBuilder sb = new StringBuilder();
		sb.append("?ids=");
		for(int i = 0;i<ids.length;i++){
			if(i!=0){
				sb.append(",");
			}
			sb.append(ids[i]);
		}
		return sb;
	}
}
