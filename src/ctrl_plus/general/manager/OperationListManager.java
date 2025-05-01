package ctrl_plus.general.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ctrl_plus.general.operationFrame.Operation;
import ctrl_plus.general.operationFrame.OperationList;

public class OperationListManager {
	private static OperationList operationList;
	private static String key;
	private static Map<String, OperationList> listMap;

	static {
		operationList = new OperationList();
		key = "default";
		if (listMap == null) {
			listMap = new HashMap<>();
		}
		switchList(key);
	}

	/**
	 * 現在選択中の命令のリストをすべて出力
	 */
	public static void printCurrentOperationList() {
		operationList.getOperationList().forEach(i -> System.out.println(i.getOperationCodeStr()));
	}

	
	/**
	 * マップのキーをすべて取得
	 * @return
	 */
	public static List<String> getKeyList() {
		List<String> keyList = new ArrayList<>();
		listMap.forEach((k, v) -> {
			keyList.add(k);
		});
		return keyList;
	}
	
	
	/**
	 * マップのキーをすべて出力
	 */
	public static void printKeyList() {
		System.out.print("Current Key List:");
		getKeyList().forEach(k -> System.out.print(key + ", "));
		System.out.println();
	}

	/**
	 * 命令スタックの一番上に命令を追加
	 * @param <T>
	 * @param operation
	 */
	public static <T extends Operation> void addOperation(T operation) {
		operationList.setNextOperation(operation);
	}

	/**
	 * 命令スタックの一番上から命令を除去
	 */
	public static void removeOperation() {
		operationList.removeTopOperation();
	}

	/**
	 * 現在のインデックスを取得
	 * 通常は(終端 - 1)
	 */
	public static int getCurrentIndex() {
		return operationList.getIndex() - 1;
	}
	
	
	/** 
	 * 現在操作中のキーを取得
	 * @return
	 */
	public static String getCurrentKey() {
		return key;
	}

	/**
	 * 現在操作しているリストを変更
	 * @param key	変更対象の命令リスト名
	 */
	public static void switchList(String destKey) {
		// マップに保存
		listMap.put(key, operationList);

		// マップから取得
		if (listMap.containsKey(destKey)) {
			// 変更先のキーが存在した場合
			operationList = listMap.get(destKey);
		} else {
			// 変更先のキーが存在しなかった場合
			/** @todo 例外処理をしたい */
			System.out.println("[ERROR]Cannot find key");
		}
		printCurrentListInfo();
	}

	public static void printCurrentListInfo() {
		System.out.print("Currently Editing: ");
		System.out.println("Key=\"" + key + "\", Index=" + operationList.getIndex());
	}

}
