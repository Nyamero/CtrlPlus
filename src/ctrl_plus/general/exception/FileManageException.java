package ctrl_plus.general.exception;

public class FileManageException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * コンストラクタ
	 * @param msg 出力メッセージ
	 */
	 public FileManageException(String msg) {
		 super(msg);
	 }
	 
	 public FileManageException() {
		 super();
	 }
}
