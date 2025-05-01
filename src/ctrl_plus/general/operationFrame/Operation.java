package ctrl_plus.general.operationFrame;

import ctrl_plus.general.model.OperationKind;

public class Operation {
	private int opNumber;
	private OperationKind op;
	
	public Operation(int opNumber, OperationKind op) {
		this.opNumber = opNumber;
		this.op = op;
	}
	
	public Operation() {
		this(-1, OperationKind.NULL);
	}

	
	public int getOpNumber() {
		return opNumber;
	}

	public void setOpNumber(int opNumber) {
		this.opNumber = opNumber;
	}

	public OperationKind getOp() {
		return op;
	}

	public void setOp(OperationKind op) {
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
