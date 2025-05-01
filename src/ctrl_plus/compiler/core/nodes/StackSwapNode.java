package ctrl_plus.compiler.core.nodes;

import ctrl_plus.compiler.core.visitor.ASTVisitor;

public class StackSwapNode extends ASTNode {
	
	@Override
	public String getType() {
		return "StackSwapNode";
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}

}
