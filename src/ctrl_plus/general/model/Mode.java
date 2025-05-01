package ctrl_plus.general.model;

public enum Mode {
	
	COPY,
	CUT,
	REDO,
	REDO_REDO,
	UNDO;
	
	public String getKindStr(Mode kind) {
		
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
