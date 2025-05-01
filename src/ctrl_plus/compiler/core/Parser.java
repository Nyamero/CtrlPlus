package ctrl_plus.compiler.core;

import ctrl_plus.general.exception.ParsingException;
import ctrl_plus.general.model.Kind;

public class Parser {

	// =============== プライベートフィールド ===============
	private Lexer lexer; // 字句解析のインスタンス
	private Token currentToken; // 現在の参照トークン

	private boolean _showTokenDetail;	// デバッグ用: トークン詳細の表示フラグ
	private boolean _showParseDetail;	// デバッグ用: 構文解析詳細の表示フラグ
	private boolean _showDebugText;		// デバッグ用: デバッグテキストの表示

	private int _depth = 0; // デバッグ用: 構文解析ログのインデント深度

	// =============== コンストラクタ ===============

	/**
	 * コンストラクタ
	 * プライベートフィールドを初期化
	 */
	public Parser(boolean _showTokenDetail, boolean _showParseDetail, boolean _showDebugText) {
		//プライベートフィールド初期化&代入
		lexer = new Lexer();
		currentToken = new Token();
		this._showTokenDetail = _showTokenDetail;
		this._showParseDetail = _showParseDetail;
		this._showDebugText = _showDebugText;

		// 最初のトークンを取得
		advance();
		// 構文解析開始
		translationUnit();
	}

	// =============== 構文解析メソッド ===============

	/**
	 * <translation-unit> ::= <declaration>
	 */
	void translationUnit() {
		enterNode();

		declaration();

		exitNode();
	}

	/**
	 * <declaration> ::= {<compound-command>}*
	 */
	void declaration() {
		enterNode();

		while (!expect(Kind.NULLTOKEN) && !expect(Kind.EOFTOKEN) && !expect(Kind.OTHER)) {
			compoundCommand();
		}

		exitNode();
	}

	/**
	 * <compound-command> ::= <command>
	 *				 		| <pointer>
	 *				 		| <heap>
	 *				 		| <binary>
	 */
	void compoundCommand() {
		enterNode();

		switch (currentToken.getKind()) {
		case REDO: // calculate もしくは stack
			advance(); // 次のトークンを読む
			command();
			break;

		case COPY:
			advance();
			pointer();
			break;

		case CUT:
			advance();
			heap();
			break;

		case UNDO:
			advance();
			binary();
			break;

		default:
			reportError("Unexpected Token");
		}

		exitNode();
	}

	/**
	 * <command> ::= <stack>
	 *			   | <calculate>
	 */
	void command() {
		enterNode();

		// 現在来ているトークン: 1つめのredoの「次」
		if (expect(Kind.REDO)) { // もう一回来た場合: stack
			advance(); // 2つめのredoを消費する
			stack(); // stackへ受け渡し
		} else { // redo以外が来た場合: スタック操作コマンド
			calculate(); // 現在参照中のredo以外のトークンを渡す
		}

		exitNode();
	}

	/**
	 * <pointer> ::= "copy" {"copy" }+ "paste"
	 * 			   | "copy" { <stack> }? { <heap> }? { "copy" }+ "paste"
	 */
	void pointer() {
		enterNode();

		int ptr = 0;

		// 現在来ているトークン: モード変更したcopyの「次」
		while (!expect(Kind.NULLTOKEN) && !expect(Kind.OTHER) && !expect(Kind.EOFTOKEN) && !expect(Kind.PASTE)) {
			switch (currentToken.getKind()) {
			case COPY: // ポインタ進める
				ptr++;
				advance();
				break;
			case PASTE: // モード終了
				break;
			case REDO: // <command>(スタック操作モードのみ許容)
				advance();
				if (expect(Kind.REDO)) {
					advance(); // 2つめのadvanceを消費
					stack(); // スタック操作モードへ
				} else {
					reportError("Only heap or stack operations can be performed during pointer operations");
				}
				break;
			case CUT: // ヒープモードへ
				advance();
				heap();
				break;
			default: // 許可されていない操作
				reportError("Only heap or stack operations can be performed during pointer operations");
			}
		}
		if (expect(Kind.PASTE)) {
			printDebug("Pointer value: " + ptr + ", Character: '");
			advance();
		} else {			
			reportError("Invalid command");
		}
		/** @todo 数値をスタックに積む */
		/** @todo Character: の次に文字の配列を付ける */

		exitNode();
	}

	/**
	 * <heap> ::= "cut" <command-identifier>
	 */
	void heap() {
		enterNode();
		// 現在来ているトークン: モード変更したcopyの「次」
		switch (currentToken.getKind()) {
		case COPY:
			/** @todo アドレス記録 */
			break;
		case PASTE:
			/** @todo アドレスはりつけ&ポップ */
			break;
		default:
			printDebug("Invalid heap command");
		}

		exitNode();
	}

	/**
	 * <binary> ::= "undo" {<command-identifier>}* "undo"
	 */
	void binary() {
		enterNode();

		// トークンチェック
		if (!expect(Kind.COPY) && !expect(Kind.PASTE) && !expect(Kind.UNDO)) {
			reportError("Invalid command in numeric mode");
		}

		// この時点でCOPY, PASTE, UNDOのいずれかであるはず

		long value = 0;
		String binStr = "";
		if (expect(Kind.COPY) || expect(Kind.PASTE)) {
			while (expect(Kind.COPY) || expect(Kind.PASTE)) {
				if (expect(Kind.COPY)) {
					value = bin2Dec(value, false);
					binStr += "0";
				} else {
					value = bin2Dec(value, true);
					binStr += "1";
				}
				advance();
			}
		}
		if (!expect(Kind.UNDO)) {
			reportError("Numeric mode must end with UNDO");
		}

		// デバッグ出力
		/** @todo 数値をスタックにプッシュ */
		printDebug("binary: " + binStr + ", value: " + value);

		advance();
		exitNode();
	}

	/**
	 * <calculate> ::= "redo" {<command-specifier>}+ {<command-specifier>}? "undo"
	 */
	void calculate() {
		enterNode();

		// 現在来ているトークン: モード変更したredoの「次」
		if (expect(Kind.EOFTOKEN)) {
			reportError("Invalid command in calculation mode");
		}

		switch (currentToken.getKind()) {
		case COPY:
			advance();
			if (expect(Kind.UNDO)) {
				// copy: 加算
				/** @todo データスタック加算処理 
				 * 数値と文字列の計算が起きたらどうしよう */
			} else if (expect(Kind.PASTE)) {
				// copy paste: 乗算
				/** @todo データスタック乗算処理 */
				advance();
				if (!expect(Kind.UNDO)) {
					reportError("Calculation command not closed");					
				}
			} else if (expect(Kind.COPY)) {
				// copy copy: 除算
				/** @todo データスタック除算処理 */
				advance();
				if (!expect(Kind.UNDO)) {
					reportError("Calculation command not closed");					
				}
			} else {
				reportError("Invalid command in calculation mode");
			}
			break;
		case PASTE:
			advance();
			if (expect(Kind.UNDO)) {
				// paste: 減算
				/** @todo データスタック減算処理 */
			} else if (expect(Kind.PASTE)) {
				// paste paste: 剰余
				/** @todo データスタック剰余処理 */
				advance();
				if (!expect(Kind.UNDO)) {
					reportError("Calculation command not closed");					
				}

			} else {
				reportError("Invalid command in calculation mode");				
			}
			break;
		default:
			reportError("Invalid command in calculation mode");
		}

		advance();	// calculate該当コマンドの次のトークンを取得
		exitNode();

	}

	/**
	 * <stack> ::= "redo" "redo" {<command-specifier>}+ {<command-specifier>}? "undo"
	 */
	void stack() {
		enterNode();

		// 現在来ているトークン: モード変更したredo redoの「次」
		if (expect(Kind.EOFTOKEN)) {
			reportError("Invalid command in calculation mode");
		}

		switch (currentToken.getKind()) {
		case COPY:
			advance();
			if (expect(Kind.UNDO)) {
				// copy: 命令スタックの一番上を複製
				/** @todo 命令スタック複製処理 */
			} else if (expect(Kind.PASTE)) {
				// copy paste: 命令スタックの一番上をコピーして上に積む
				/** @todo 命令スタックコピーして載せる処理 */
				advance();
				if (!expect(Kind.UNDO)) {
					reportError("Stack command not closed");					
				}
			} else if (expect(Kind.COPY)) {
				// copy copy: 命令スタックの一番上と二番目を交換
				/** @todo 命令スタックの交換処理 */
				advance();
				if (!expect(Kind.UNDO)) {
					reportError("Stack command not closed");					
				}
			} else {
				reportError("Invalid command in stack mode");
			}
			break;
		case PASTE:
			advance();
			if (expect(Kind.UNDO)) {
				// paste: 命令スタックの一番上を捨てる
				/** @todo 命令スタック廃棄処理 */
			} else if (expect(Kind.PASTE)) {
				// paste paste: データスタックの一番上を出力
				/** @todo データスタック出力処理 */
				advance();
				if (!expect(Kind.UNDO)) {
					reportError("Stack command not closed");					
				}
			} else {
				reportError("Invalid command in stack mode");				
			}
			break;
		default:
			reportError("Invalid command in stack mode");
		}

		advance();	// stack該当コマンドの次のトークンを取得
		exitNode();
	}

	// =============== ユーティリティメソッド ===============

	/**
	 * @param なし
	 * 次のトークンを取得
	 */
	private void advance() {
		currentToken = lexer.nextToken();
		if (_showTokenDetail) {
			_printIndent();
			System.out.print("[[ADVANCE]]");
			_showToken();
		}
	}

	/**
	 * トークン種別の正誤判定
	 * @param expectedKind
	 * @return boolean
	 */
	private boolean expect(Kind expectedKind) {
		try {
			if (currentToken.getKind().equals(expectedKind)) {
				return true;
			}			
		} catch (Exception e) {
			return false;			
		}
		return false;
	}

	private long bin2Dec(long current, boolean isOne) {
		if (isOne) {
			return (current * 2) + 1;
		} else {
			return (current * 2);
		}
	}

	/**
	 * エラーリポート
	 * @param message : エラーメッセージ内容
	 * フォーマット: "[Parse Error]" + message
	 */
	private void reportError(String message) {
		throw new ParsingException("[Parse Error]" + message);
	}

	// =============== デバッグ用メソッド ===============

	/**
	 * デバッグ用: トークン情報表示
	 */
	private void _showToken() {
		if (_showTokenDetail) {
			if (_showParseDetail) {
				_printIndent();				
			}
			currentToken.printToken();
		}
	}

	/**
	 * デバッグ用: 構文解析ログの出力
	 * ノード開始時ログ
	 */
	private void enterNode() {
		if (_showParseDetail) {
			// インデント増加
			_depth++;
			// インデント作成
			_printIndent();
			// 開始するノードと現在のトークンを表示
			System.out.print("-> " + calledFrom() + ", ");
			_showToken();
		}
	}

	/**
	 * デバッグ用: 構文解析ログの出力
	 * ノード脱出時ログ
	 */
	private void exitNode() {
		if (_showParseDetail) {
			// インデント減少
			_depth--;
			// インデント作成
			_printIndent();
			// 脱出するノードを表示
			System.out.println("<- " + calledFrom());
		}
	}

	/**
	 * デバッグ用: 呼び出し元メソッド取得
	 * 該当メソッドがなければ空文字を返す
	 * 
	 * @return メソッド名
	 */
	public static String calledFrom() {
		StackTraceElement[] steArray = Thread.currentThread().getStackTrace();
		if (steArray.length <= 3) {
			return "";
		}
		StackTraceElement ste = steArray[3];
		StringBuilder sb = new StringBuilder();
		sb.append(ste.getMethodName()); // メソッド名取得

		return sb.toString();
	}
	
	
	/**
	 * デバッグ用: インデント表示
	 */
	public void _printIndent() {
		for (int i = 0; i < _depth; i++) {
			System.out.print(" ");
		}
	}

	/**
	 * デバッグ用: デバッグ情報を出力
	 * @param デバッグメッセージ
	 * フォーマット: "[DEBUG(parser)]" + message
	 */
	public void printDebug(String message) {
		if (_showDebugText) {
			if (_showParseDetail) {
				_printIndent();
			}
			System.out.println("[DEBUG(parser)]" + message);
		}
	}

}
