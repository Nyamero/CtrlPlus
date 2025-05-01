package ctrl_plus.general.operationFrame;

import ctrl_plus.general.enums.EnumOperations;

public class Operation {
	private int opNumber;
	private EnumOperations op;
	
	public Operation(int opNumber, EnumOperations op) {
		this.opNumber = opNumber;
		this.op = op;
	}
	
	public Operation() {
		this(-1, EnumOperations.NULL);
	}

	
	public int getOpNumber() {
		return opNumber;
	}

	public void setOpNumber(int opNumber) {
		this.opNumber = opNumber;
	}

	public EnumOperations getOp() {
		return op;
	}

	public void setOp(EnumOperations op) {
		this.op = op;
	}
	
	
	/**
	 * @return 命令コードの文字列
	 * 
	 * OPSTR
	 */
	public String getOperationCodeStr() {
		return op.getKindStr(op);
	}
	
	
}
