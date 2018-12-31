package Error;
	
	public class LignInvalid extends RuntimeException{

		public LignInvalid() {
			System.out.println("Arguments faux !");
		}

		public LignInvalid(String arg0) {
			super(arg0);
			
		}

		public LignInvalid(Throwable arg0) {
			super(arg0);
			
		}

		public LignInvalid(String message, Throwable cause) {
			super(message, cause);
			
		}

		public LignInvalid(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
			
		}

}
