package ctrl_plus.general.enums;

public enum EnumOperatorKind {
	ADD,
	SUB,
	MUL,
	DIV,
	MOD;
	
	public static String getKindStr(EnumOperatorKind kind) {
		switch (kind) {
		case ADD: return "ADD";
		case SUB: return "SUB";
		case MUL: return "MUL";
		case DIV: return "DIV";
		case MOD: return "MOD";
		
		default: return "OTHER";
		}
		
	}
}
