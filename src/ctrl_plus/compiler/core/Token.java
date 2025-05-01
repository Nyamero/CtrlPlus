package ctrl_plus.compiler.core;

import ctrl_plus.general.model.Kind;

public class Token {
	
	// プライベート フィールド
	private String str;
	private Kind kind;
	
	// コンストラクタ
	public Token(Kind kind, String str) {
		this.kind = kind;
		this.str = str;
	}
	
	public Token(Kind kind) {
		this(kind, "");
	}
	
	public Token() {
		this(Kind.NULLTOKEN, "");
	}

	// セッター
	public void setStr(String str) {
		this.str = str;
	}
	
	public void setKind(Kind kind) {
		this.kind = kind;
	}
	
	// ゲッター
	public String getStr() {
		return str;
	}
	
	public Kind getKind() {
		return kind;
	}
	
	public void printToken() {
		try {
			System.out.println(str + ", " + kind.getKindStr(kind));			
		} catch (NullPointerException e) {
			return;
		}
	}
}
