package ctrl_plus.compiler.core.nodes;

import ctrl_plus.compiler.core.visitor.ASTVisitor;
import ctrl_plus.general.model.Mode;

public class ModeSwitchNode extends ASTNode {

	private final Mode mode;
	
	/**
	 * コンストラクタ
	 * コマンド種別を指定
	 */
	public ModeSwitchNode(Mode mode) {
		this.mode = mode;
	}
	
	public Mode getMode() {
		return mode;
	}
	
	@Override
	public String getType() {
		return "CommandNode";
	}

	@Override
	public void accept(ASTVisitor visitor) {
		visitor.visit(this);
	}

}
