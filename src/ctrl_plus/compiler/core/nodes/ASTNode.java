package ctrl_plus.compiler.core.nodes;

import java.util.ArrayList;
import java.util.List;

import ctrl_plus.compiler.core.visitor.ASTVisitor;

public abstract class ASTNode {
	
	private final List<ASTNode> children = new ArrayList<>();
	
	public void addChild(ASTNode child) {
		children.add(child);
	}
	
	public List<ASTNode> getChildren() {
		return children;
	}
	
	/**
	 * ノードの種類を判別
	 * @return ノード種別
	 */
	public abstract String getType();
	
	/**
	 * トラバース処理に使用
	 * @param visitor
	 * クラスごとに異なる処理をする(命令生成、最適化、デバッグ出力など)
	 * for文で管理するとノードの種類が増えるたびに分岐が増える(if instanceofだらけは冗長)
	 * そのため、各ノードが自分に合った処理をVisitorにゆだねる
	 */
	public abstract void accept(ASTVisitor visitor);
}
