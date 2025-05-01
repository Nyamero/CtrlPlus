package ctrl_plus.general.exception;

public class ParsingException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * コンストラクタ
	 * @param msg 出力メッセージ
	 */
	 public ParsingException(String msg) {
		 super(msg);
	 }
	 
	 public ParsingException() {
		 super();
	 }

}
