package ctrl_plus.general.manager;

import java.util.ArrayList;
import java.util.List;

public class LogManager {

	/**
	 * ロガーレベルの種類を定義<br>
	 * <p>ロガーレベルの種類は以下の4通り:
	 * <ul>
	 * <li>DEBUG 	(低)</li>
	 * <li>INFO		(中)</li>
	 * <li>WARN		(高)</li>
	 * <li>ERROR	(最高)</li>
	 * </ul>
	 * 設定されたレベル以上のログが出力される</p>
	 */
	public enum LoggerLevel {
		DEBUG(0), // 低
		INFO(1), // 中
		WARN(2), // 高
		ERROR(3); // 最高

		private final int level;

		LoggerLevel(int level) {
			this.level = level;
		}

		public int getLevel() {
			return level;
		}

	}
	
	// シングルトンインスタンスを保持
	private static final LogManager INSTANCE = new LogManager();

	// ログを集積
	private List<String> logList;
	// ロガー・レベル
	private LoggerLevel loggerLevel;
	// 初期化の有無
	private boolean initialized = false;
	
	/**
	 * LogManagerコンストラクタ
	 * <p>ログリストとロガー・レベルの初期化を行う<br>
	 * <li>デフォルト・ロガー・レベル：INFO</li></p>
	 */
	private LogManager() {
		this.logList = new ArrayList<>();
	}
	
	/**
	 * LogManagerインスタンスの取得
	 */
	public static LogManager getInstance() {
		return INSTANCE;
	}

	/**
	 * LogManager使用時の初期化を行う
	 * @param loggerLevel ロガー・レベル
	 * @throws IllegalStateException
	 * <p>logListの初期化とログ出力レベルの階層を設定<br>
	 * プログラム全体で一度のみ実行可能で、二度目以降は例外をスロー</p>
	 */
	public synchronized void init(LoggerLevel level) {
		if (initialized) {
			throw new IllegalStateException("LogManager has already been initialized.");
		}
		this.logList.clear();
		this.loggerLevel = level;
		this.initialized = true;
		
		System.out.println("Logger initialized with " + level.name() + " level.");
	}

	/**
	 * DEBUGレベルのログを出力
	 * @param message
	 * <p>ログを標準出力し、ログリストに集積<br>
	 * フォーマット: [DEBUG]message</p>
	 */
	public void logDebug(String message) {
		log(LoggerLevel.DEBUG, message, false);
	}

	/**
	 * INFOレベルのログを出力
	 * @param message
	 * <p>
	 * ログを標準出力し、ログリストに集積<br>
	 * フォーマット: [INFO]message
	 * </p>
	 */
	public void logInfo(String message) {
		log(LoggerLevel.INFO, message, false);
	}

	/**
	 * WARNレベルのログを出力
	 * @param message
	 * <p>
	 * ログを標準出力し、ログリストに集積<br>
	 * フォーマット: [WARN]message
	 * </p>
	 */
	public void logWarn(String message) {
		log(LoggerLevel.WARN, message, false);
	}

	/**
	 * ERRORレベルのログを出力
	 * @param message
	 * <p>
	 * ログを標準出力し、ログリストに集積<br>
	 * フォーマット: [ERROR]message
	 * </p>
	 */
	public void logError(String message) {
		log(LoggerLevel.ERROR, message, true);
	}
	
	private void log(LoggerLevel level, String message, boolean isError) {
		ensureInitialized();
		
		String formatted = "[" + level.name() + "]" + message;
		if (checkLoggerLevel(level)) {
			if (isError) {
				System.err.println(formatted);
			} else {
				System.out.println(formatted);
			}
		}
		
		logList.add(formatted);
	}

	/**
	 *
	 * @param level
	 * @return ログ有効: true, ログ無効:false
	 * 
	 */
	private boolean checkLoggerLevel(LoggerLevel level) {
		// 比較対象のロガーレベルが現在のレベルより高ければ出力可能
		if (loggerLevel.getLevel() <= level.getLevel()) {
			return true;
		}
		return false;
	}

	/**
	 * ログリストを取得
	 * @return ログリスト
	 */
	public List<String> getLogList() {
		return logList;
	}
	
	
	private void ensureInitialized() {
		if (!initialized) {
			throw new IllegalStateException("LogManager must be initialized before use.");
		}
	}
}
