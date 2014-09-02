package info.re4k.asfc.aclog;

public class AclogConfig{
	private static int connectTimeoutDef = 20*1000;
	private static int readTimeoutDef = 120*1000;

	protected int connectTimeOut = connectTimeoutDef;
	protected int resdTimeout = readTimeoutDef;

	public AclogConfig(){}

	public static class Builder{

		private int connectTimeout = -1;
		private int readTimeout = -1;

		public Builder(){}

		public Builder setConnectTimeout(int time){
			this.connectTimeout = time;
			return this;
		}

		public Builder setReadTimeout(int time){
			this.readTimeout = time;
			return this;
		}

		public AclogConfig build(){
			AclogConfig config = new AclogConfig();
			if(this.connectTimeout!=-1){
				config.connectTimeOut = this.connectTimeout;
			}
			if(this.readTimeout!=-1){
				config.resdTimeout = this.readTimeout;
			}
			return config;
		}

	}
}
