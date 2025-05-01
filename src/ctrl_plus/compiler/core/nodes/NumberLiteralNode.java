package ctrl_plus.compiler.core.nodes;

import ctrl_plus.compiler.core.visitor.ASTVisitor;

public class NumberLiteralNode extends ASTNode {
	
	private final int value;
	
	public NumberLiteralNode(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String getType() {
		return "NumberLiteralNode";
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}

}
