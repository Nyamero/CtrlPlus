package ctrl_plus.general.model;

public enum OperationKind {
	
	OTHER,
	NULL;
	
	
	public String getKindStr(OperationKind kind) {
		switch(kind) {
		default: return "OTHER";
		}
	}
}
