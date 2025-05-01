package ctrl_plus.general.enums;

public enum EnumKind {
	
	COPY,
	PASTE,
	CUT,
	REDO,
	UNDO,
	LETTER,
	OTHER,
	NULLTOKEN,
	EOFTOKEN;
	
	public String getKindStr(EnumKind kind) {
		
		switch (kind) {
		case COPY:			return "COPY";
		case PASTE: 		return "PASTE";
		case CUT: 			return "CUT";
		case REDO: 			return "REDO";
		case UNDO:			return "UNDO";
		case LETTER: 		return "LETTER";
		case OTHER:			return "OTHER";
		case NULLTOKEN:	return "NULLTOKEN";
		case EOFTOKEN:		return "EOFTOKEN";

		default: 			return "OTHER";
		}
	}
}
