package ctrl_plus.compiler.core.nodes;

import ctrl_plus.compiler.core.visitor.ASTVisitor;
import ctrl_plus.general.model.Operator;

public class ArithmeticNode extends ASTNode {
	
	

	private final Operator operator;
	
	public ArithmeticNode(Operator operator) {
		this.operator = operator;
	}

	public Operator getOperator() {
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
