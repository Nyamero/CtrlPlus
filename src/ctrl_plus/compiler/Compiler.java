package ctrl_plus.compiler;

import ctrl_plus.compiler.core.Parser;
import ctrl_plus.general.exception.LexicalAnalysisException;
import ctrl_plus.general.exception.ParsingException;
import ctrl_plus.general.manager.ErrorManager;
import ctrl_plus.general.manager.InputManager;

public class Compiler {
	
	public static void compile() {
		
		InputManager.getInstance().getStr();
		
		try {
			new Parser(true, true, true);
		
		} catch (LexicalAnalysisException | ParsingException e) {
			ErrorManager.syntaxError(e);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		System.out.println("\n... Compilation Finished");
	}
}
