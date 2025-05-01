package ctrl_plus.compiler.core.nodes;

import ctrl_plus.compiler.core.visitor.ASTVisitor;

public class JumpMarkNode extends ASTNode {

	@Override
	public String getType() {
		return "JumpMarkNode";
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}

}
