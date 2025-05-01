package ctrl_plus.general.operationFrame;

import ctrl_plus.general.model.OperationKind;

/**
 * JMPTOなど
 */
public class SingleDestOp extends Operation {
	
	private int dest1;

	public SingleDestOp(int opNumber, OperationKind op, int dest1) {
		super(opNumber, op);
		this.dest1 = dest1;
	}

	
	public int getDest1() {
		return dest1;
	}

	public void setDest1(int dest1) {
		this.dest1 = dest1;
	}
	
	/**
	 * @return 命令コードの文字列
	 * 
	 * OPSTR dest1
	 */
	@Override
	public String getOperationCodeStr() {
		return this.getOp().getKindStr(this.getOp()) + " " + this.dest1;
	}

	
}
