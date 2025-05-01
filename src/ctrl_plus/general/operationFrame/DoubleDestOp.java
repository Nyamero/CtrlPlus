package ctrl_plus.general.operationFrame;

import ctrl_plus.general.model.OperationKind;

/**
 * ADD, MULなど
 */
public class DoubleDestOp extends SingleDestOp {
	
	private int dest2;

	public DoubleDestOp(int opNumber, OperationKind op, int dest1, int dest2) {
		super(opNumber, op, dest1);
		this.dest2 = dest2;
	}

	
	public int getDest2() {
		return dest2;
	}

	public void setDest2(int dest2) {
		this.dest2 = dest2;
	}

	/**
	 * @return 命令コードの文字列
	 * 
	 * OPSTR dest1 dest2
	 */
	@Override
	public String getOperationCodeStr() {
		return this.getOp().getKindStr(this.getOp()) + " " + this.getDest1() + "" + this.dest2;
	}
	
}
