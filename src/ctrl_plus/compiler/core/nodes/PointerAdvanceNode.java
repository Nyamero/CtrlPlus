package ctrl_plus.compiler.core.nodes;

import ctrl_plus.compiler.core.visitor.ASTVisitor;

public class PointerAdvanceNode extends ASTNode {
	
	@Override
	public String getType() {
		return "PointerMoveNode";
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}

}
