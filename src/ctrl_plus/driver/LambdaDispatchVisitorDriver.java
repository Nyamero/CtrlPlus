package ctrl_plus.driver;

import ctrl_plus.compiler.core.nodes.ASTNode;
import ctrl_plus.compiler.core.nodes.ArithmeticNode;
import ctrl_plus.compiler.core.nodes.PointerAdvanceNode;
import ctrl_plus.compiler.core.nodes.PointerPushNode;
import ctrl_plus.compiler.core.nodes.RootNode;
import ctrl_plus.compiler.core.visitor.LambdaDispatchVisitor;

public class LambdaDispatchVisitorDriver {

	public static void main(String[] args) {

		LambdaDispatchVisitor visitor = new LambdaDispatchVisitor();
		
		// 登録例
		visitor.registerHandler(PointerAdvanceNode.class, node ->
				System.out.println("emit: copy"));
		visitor.registerHandler(PointerPushNode.class, node ->
				System.out.println("emit: paste"));
		visitor.registerHandler(ArithmeticNode.class, node -> {
			switch(((ArithmeticNode) node).getOperator()) {
			case ADD -> System.out.println("emit: ADD");
			case SUB -> System.out.println("emit: ADD");
			case MUL -> System.out.println("emit: ADD");
			case DIV -> System.out.println("emit: ADD");
			case MOD -> System.out.println("emit: ADD");
			}
		});

		ASTNode root = new RootNode();
//		root.addChild(new ModeSwitchNode(EnumModeKind.COPY).addChild(new PointerAdvanceNode()));
	}
	
	
	

}
