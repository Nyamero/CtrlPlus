package ctrl_plus.compiler.core.nodes;

import ctrl_plus.compiler.core.visitor.ASTVisitor;
import ctrl_plus.general.enums.EnumModeKind;

public class ModeSwitchNode extends ASTNode {

	private final EnumModeKind mode;
	
	/**
	 * コンストラクタ
	 * コマンド種別を指定
	 */
	public ModeSwitchNode(EnumModeKind mode) {
		this.mode = mode;
	}
	
	public EnumModeKind getMode() {
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
