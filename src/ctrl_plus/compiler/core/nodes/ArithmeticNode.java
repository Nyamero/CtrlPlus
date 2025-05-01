package ctrl_plus.compiler.core.nodes;

import ctrl_plus.compiler.core.visitor.ASTVisitor;
import ctrl_plus.general.enums.EnumOperatorKind;

public class ArithmeticNode extends ASTNode {

	private final EnumOperatorKind operator;
	
	public ArithmeticNode(EnumOperatorKind operator) {
		this.operator = operator;
	}

	public EnumOperatorKind getOperator() {
		return operator;
	}
	
	@Override
	public String getType() {
		return "ArithmeticNode";
	}

	@Override
	public void accept(ASTVisitor visitor) {
				visitor.visit(this);
	}

}
