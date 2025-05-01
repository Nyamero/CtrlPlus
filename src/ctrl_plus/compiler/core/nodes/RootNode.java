package ctrl_plus.compiler.core.nodes;

import java.util.ArrayList;
import java.util.List;

import ctrl_plus.compiler.core.visitor.ASTVisitor;

public class RootNode extends ASTNode {

	private final List<ASTNode> children = new ArrayList<>();
	
	public void add(ASTNode node) {
		children.add(node);
	}
	
	public List<ASTNode> getChildren() {
		return children;
	}
	
	
	
	@Override
	public String getType() {
		return "RootNode";
	}

	@Override
	public void accept(ASTVisitor visitor) {
		
		visitor.visit(this);
	}

}
