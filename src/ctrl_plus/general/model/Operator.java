package ctrl_plus.general.model;

public enum Operator {
	ADD,
	SUB,
	MUL,
	DIV,
	MOD;
	
	public static String getKindStr(Operator kind) {
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
