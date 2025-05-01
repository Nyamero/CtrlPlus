package ctrl_plus.compiler.core.visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import ctrl_plus.compiler.core.nodes.ASTNode;
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

public class LambdaDispatchVisitor implements ASTVisitor {

	/**
	 * Visitorの代替として動的切り替えできる処理を登録するMap
	 */
	private final Map<Class<? extends ASTNode>, Consumer<? extends ASTNode>> handlers = new HashMap<>();
	
//	/**
//	 * コンストラクタ
//	 * 新しいマップをインスタンス化
//	 */
//	public LambdaDispatchVisitor() {
//		this.handlers = new HashMap<>();
//	}
	
	/**
	 * 処理を登録
	 * @param <T>
	 * @param nodeType
	 * @param handler
	 * 
	 * 処理の数だけLambdaをMapに登録することでメソッド数の削減を実現
	 * 処理の切り替えもMap変更で実現
	 * Visitorロジックの最小化も狙う
	 * 	- dispatch()の処理が一か所に集中するため保守性が向上
	 */
	public <T extends ASTNode> void registerHandler(Class<T> nodeType, Consumer<T> handler) {
		handlers.put(nodeType, handler);
	}
	
	
	/**
	 * ハンドラーを登録
	 * @param <T> ASTNodeを継承したクラス
	 * @param node
	 */
	private <T extends ASTNode> void dispatch(T node) {
//		System.out.println("[DEBUG] dispatch called for: " + node.getClass().getName());
		
		boolean matched = false;
		for (Map.Entry<Class<? extends ASTNode>, Consumer<? extends ASTNode>> entry : handlers.entrySet()) {
//			System.out.println("[DEBUG] checking handler for: " + entry.getKey().getName());
			if (entry.getKey().isAssignableFrom(node.getClass())) {
//				System.out.println("[DEBUG] matched handler: " + entry.getKey().getName());
				Consumer<T> handler = (Consumer<T>) entry.getValue();
				
				// ハンドラーに登録した処理を実行
				handler.accept(node);
				// 成功した時点で終了
				return;
			}
		}
		
		// 一度もマッチしなかった場合のみエラー表示
		System.err.println("[WARN] No Handler registered for node type: ");
	}
	
//	private <T extends ASTNode> void dispatch(T node) {
//		// 処理を格納
//		Consumer<T> handler = (Consumer<T>) handlers.get(node.getClass());
//		if (handler != null) {
//			handler.accept(node);
//		} else {
//			// 処理が登録されていなかった場合のエラー出力
//			System.err.println("[WARN] No Handler registered for node type: ");
//		}
//	}
	
	/* -------- デバッグ用メソッド -------- */
	public void dumpRegsteredHandlers() {
		System.out.println("[INFO] Registered handler types:");
		for (Class<? extends ASTNode> key : handlers.keySet()) {
			System.out.println(" - " + key.getName());
		}
	}
	
	
	/* ======== OVERRIDE METHODS ======== */
	
	@Override
	public void visit(RootNode node) {
		dispatch(node);
	}

	@Override
	public void visit(ModeSwitchNode node) {
		dispatch(node);
	}

	@Override
	public void visit(PointerAdvanceNode node) {
		dispatch(node);
	}

	@Override
	public void visit(PointerPushNode node) {
		dispatch(node);
	}

	@Override
	public void visit(JumpMarkNode node) {
		dispatch(node);
	}

	@Override
	public void visit(JumpExecuteNode node) {
		dispatch(node);
	}

	@Override
	public void visit(ArithmeticNode node) {
		dispatch(node);
	}

	@Override
	public void visit(StackSwapNode node) {
		dispatch(node);
	}

	@Override
	public void visit(StackDropNode node) {
		dispatch(node);
	}

	@Override
	public void visit(StackCopyTopNode node) {
		dispatch(node);
	}

	@Override
	public void visit(StackPrintNode node) {
		dispatch(node);
	}

	@Override
	public void visit(NumberLiteralNode node) {
		dispatch(node);
	}

}
