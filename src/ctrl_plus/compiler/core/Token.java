package ctrl_plus.compiler.core;

import ctrl_plus.general.enums.EnumKind;

public class Token {
	
	// プライベート フィールド
	private String str;
	private EnumKind kind;
	
	// コンストラクタ
	public Token(EnumKind kind, String str) {
		this.kind = kind;
		this.str = str;
	}
	
	public Token(EnumKind kind) {
		this(kind, "");
	}
	
	public Token() {
		this(EnumKind.NULLTOKEN, "");
	}

	// セッター
	public void setStr(String str) {
		this.str = str;
	}
	
	public void setKind(EnumKind kind) {
		this.kind = kind;
	}
	
	// ゲッター
	public String getStr() {
		return str;
	}
	
	public EnumKind getKind() {
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
