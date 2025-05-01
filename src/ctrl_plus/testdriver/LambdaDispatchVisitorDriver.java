package ctrl_plus.testdriver;

import ctrl_plus.compiler.core.nodes.ASTNode;
import ctrl_plus.compiler.core.nodes.ArithmeticNode;
import ctrl_plus.compiler.core.nodes.NumberLiteralNode;
import ctrl_plus.compiler.core.nodes.RootNode;
import ctrl_plus.compiler.core.visitor.LambdaDispatchVisitor;
import ctrl_plus.general.model.Operator;

public class LambdaDispatchVisitorDriver {

	public static void main(String[] args) {
		
		// ASTを手動で構築
		RootNode root = new RootNode();
		root.addChild(new NumberLiteralNode(5));
		root.addChild(new NumberLiteralNode(4));
		root.addChild(new NumberLiteralNode(3));
		root.addChild(new ArithmeticNode(Operator.ADD));	// 4 + 3
		root.addChild(new ArithmeticNode(Operator.MUL));	// 5 * (4 + 3)
		
		// LambdaDispatchVisitorのインスタンス作成
		LambdaDispatchVisitor visitor = new LambdaDispatchVisitor();
		
		// 命令ハンドラを登録
		visitor.registerHandler(RootNode.class, node -> {
			for (ASTNode child : node.getChildren()) {
				child.accept(visitor);
			}
		});
				
		
		visitor.registerHandler(NumberLiteralNode.class, node -> {
			int value = node.getValue();
			System.out.println("emit: push " + value);
		});
		
		visitor.registerHandler(ArithmeticNode.class, node -> {
			Operator op = node.getOperator();
			String opStr = Operator.getKindStr(op);
			System.out.println("emit: " + opStr);
		});
		
		// トラバース実行
		root.accept(visitor);
		
		
		visitor.dumpRegsteredHandlers();
	}
	
	
	

}
