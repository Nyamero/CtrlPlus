package ctrl_plus.general.enums;

public enum EnumModeKind {
	
	COPY,
	CUT,
	REDO,
	REDO_REDO,
	UNDO;
	
	public String getKindStr(EnumModeKind kind) {
		
		switch (kind) {
		case COPY:			return "COPY";
		case CUT: 			return "CUT";
		case REDO: 			return "REDO";
		case REDO_REDO: 		return "REDO_REDO";
		case UNDO:			return "UNDO";

		default: 			return "OTHER";
		}
	}
}
