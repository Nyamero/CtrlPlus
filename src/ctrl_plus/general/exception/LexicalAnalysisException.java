package ctrl_plus.general.exception;

public class LexicalAnalysisException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * コンストラクタ
	 * @param msg 出力メッセージ
	 */
	 public LexicalAnalysisException(String msg) {
		 super(msg);
	 }
	 
	 public LexicalAnalysisException() {
		 super();
	 }

}
