package ctrl_plus.general.model;

public enum Kind {
	
	COPY,
	PASTE,
	CUT,
	REDO,
	UNDO,
	LETTER,
	OTHER,
	NULLTOKEN,
	EOFTOKEN;
	
	public String getKindStr(Kind kind) {
		
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
