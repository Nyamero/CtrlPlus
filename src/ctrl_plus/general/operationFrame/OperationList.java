package ctrl_plus.general.operationFrame;

import java.util.ArrayList;
import java.util.List;

public class OperationList {
	
	private List<Operation> operationList = new ArrayList<>();;
	private int index;
	
	public OperationList() {
		
		this.index = 0;
	}
	
	/**
	 * 命令リストに命令を追加
	 * @param <T> Operationの子クラス(上限境界：Operation) 
	 * @param op 命令
	 * インデックスも自動でインクリメントされる
	 */
	public <T extends Operation> void setNextOperation(T op) {
		operationList.add(op);
		setEndIndex();
	}
	
	
	/**
	 * 命令リストの一番上を消去
	 */
	public void removeTopOperation() {
		operationList.remove(index);
		setEndIndex();
	}
	
	
	/**
	 * インデックスを配列の終端に設定
	 */
	private void setEndIndex() {
		index = operationList.size() - 1;
	}
	
	
	/**
	 * インデックスのセッター
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * 命令リストのゲッター
	 * @return	命令リスト
	 */
	public List<Operation> getOperationList() {
		return operationList;
	}
	
	
	/**
	 * 現在のインデックスのゲッター
	 * @return	インデックス
	 */
	public int getIndex() {
		return index;
	}
	
	
	

}
