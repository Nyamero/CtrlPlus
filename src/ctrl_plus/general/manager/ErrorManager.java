package ctrl_plus.general.manager;

public class ErrorManager {
	
	/**
	 * シンタックスエラーの管理
	 * @param e<br>
	 * フォーマット：
	 * Syntax error(line 行, column 列):<br>
	 *  >>> エラーメッセージ
	 */
	public static void syntaxError(Exception e) {
		LogManager.getInstance().logError("Syntax error(line " + InputManager.getInstance().getLine() + ", column " + InputManager.getInstance().getColumn() + "):\n >>>" + e.getMessage());
	}
	
	
	/**
	 * ファイルエラーの管理
	 * @param e<br>
	 * フォーマット : 
	 * File Error: <br>
	 *  >>> エラーメッセージ
	 */
	public static void fileError(Exception e) {
		LogManager.getInstance().logError("File error: \n >>>" + e.getMessage());
	}
}