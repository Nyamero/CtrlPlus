package ctrl_plus.testdriver;

import ctrl_plus.general.manager.LogManager;

public class LoggerDriver {

	public static void main(String[] args) {
		
		LogManager logger = LogManager.getInstance();
		
		// 1. 初期化せずログ出力
		try {
			System.out.println("=== Test 1: Use before init ===");
			logEach();
		} catch (IllegalStateException e) {
			System.out.println("Caught expected exception: " + e.getMessage());
		}
		
		// 2. 正常な初期化
		System.out.println("\n=== Test 2: Initialize ===");
		logger.init(LogManager.LoggerLevel.INFO);
		
		// 3. 初期化を行いログ出力: INFO
		System.out.println("\n=== Test 3: Log output at different levels ===");
		logEach();
		
		// 4. ログリストの正常出力確認
		System.out.println("\n=== Test 4: Display logList contents ===");
		logger.getLogList().forEach(index -> System.out.println(index));

		// 5. 二度目の初期化
		System.out.println("\n=== Test 5: Re-initialize ===");
		try {
			logger.init(LogManager.LoggerLevel.INFO);			
		} catch (IllegalStateException e) {
			System.out.println("Caught expected exception on re-init: " + e.getMessage());
		}
		
	}
	
	public static void logEach() {
		LogManager.getInstance().logDebug("this is a Debug message.");
		LogManager.getInstance().logInfo("this is Info message.");
		LogManager.getInstance().logWarn("this is Warn message.");
		LogManager.getInstance().logError("this is Error message.");
		System.out.println("=============");
	}

}
