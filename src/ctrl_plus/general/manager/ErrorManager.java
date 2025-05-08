package ctrl_plus.general.manager;

public class ErrorManager {
	
	/**
	 * シンタックスエラーの管理
	 * @param e
	 * フォーマット：
	 * Syntax error(line 行, column 列):
	 *  >>> エラーメッセージ
	 */
	public static void syntaxError(Exception e) {
		LogManager.getInstance().logError("Syntax error(line " + InputManager.getInstance().getLine() + ", column " + InputManager.getInstance().getColumn() + "):\n >>>" + e.getMessage());
	}
}