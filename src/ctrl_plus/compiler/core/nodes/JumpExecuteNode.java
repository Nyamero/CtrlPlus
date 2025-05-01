package ctrl_plus.compiler.core.nodes;

import ctrl_plus.compiler.core.visitor.ASTVisitor;

public class JumpExecuteNode extends ASTNode {

	@Override
	public String getType() {
		return "JumpExecuteNode";
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}

}
