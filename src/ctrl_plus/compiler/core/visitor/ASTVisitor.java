package ctrl_plus.compiler.core.visitor;

import ctrl_plus.compiler.core.nodes.ArithmeticNode;
import ctrl_plus.compiler.core.nodes.JumpExecuteNode;
import ctrl_plus.compiler.core.nodes.JumpMarkNode;
import ctrl_plus.compiler.core.nodes.ModeSwitchNode;
import ctrl_plus.compiler.core.nodes.NumberLiteralNode;
import ctrl_plus.compiler.core.nodes.PointerAdvanceNode;
import ctrl_plus.compiler.core.nodes.PointerPushNode;
import ctrl_plus.compiler.core.nodes.RootNode;
import ctrl_plus.compiler.core.nodes.StackCopyTopNode;
import ctrl_plus.compiler.core.nodes.StackDropNode;
import ctrl_plus.compiler.core.nodes.StackPrintNode;
import ctrl_plus.compiler.core.nodes.StackSwapNode;

public interface ASTVisitor {
	
	void visit(RootNode node);
	
	void visit(ModeSwitchNode node);
	
	void visit(PointerAdvanceNode node);
	
	void visit(PointerPushNode node);
	
	void visit(JumpMarkNode node);
	
	void visit(JumpExecuteNode node);
	
	void visit(ArithmeticNode node);
	
	void visit(StackSwapNode node);
	
	void visit(StackDropNode node);
	
	void visit(StackCopyTopNode node);
	
	void visit(StackPrintNode node);
	
	void visit(NumberLiteralNode node);
	
}
