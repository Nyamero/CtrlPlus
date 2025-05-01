package ctrl_plus.compiler.core;

import java.util.Arrays;

import ctrl_plus.general.exception.LexicalAnalysisException;
import ctrl_plus.general.manager.InputManager;
import ctrl_plus.general.model.Kind;

public class Lexer {

	// プライベート フィールド
	private Kind[] charKind = new Kind[128];
	
	// キーワード配列
	private Keyword[] keywordTable = new Keyword[5];
	
	/**
	 * コンストラクタ
	 */
	public Lexer() {
		initialize();
	}

	/**
	 * 次のトークンを読み取る
	 */
	public Token nextToken() {

		Token token = new Token();	// トークンを保持
		char currentChar = ' ';	// token.strに代入される文字列を保持
		String tokenStr = "";	// token.valに代入される値を保持
		Kind kind = Kind.NULLTOKEN;
		
		// 空白の読み飛ばし
		while (Character.isWhitespace(currentChar)) {
			currentChar = InputManager.getInstance().getNextChar();
		}

		// EOFチェック
		if (currentChar == '\0') {
			return new Token(Kind.EOFTOKEN, "EOF");
		}
		
		// トークンの切り出し
		switch (charKind[currentChar]) {
			// 文字トークン(これのみ受理)
			case LETTER:
				// 文字トークンが続くまで継続
				while(charKind[currentChar] == Kind.LETTER && currentChar != '\0') {
					tokenStr += currentChar;
					// System.out.println("[DEBUG]" + tokenStr);
					
					// 空白なしトークン分割用のトークン種類判別
					kind = findKind(tokenStr);
					// トークン分割できる場合は脱出
					if (!kind.equals(Kind.NULLTOKEN) && !kind.equals(Kind.OTHER) && !kind.equals(Kind.EOFTOKEN)) {
						break;
					}
					// トークン分割できなければ次の文字を読む(static charで次の文字を保持できないため)
					currentChar = InputManager.getInstance().getNextChar();
				}
				// 該当トークンが見つかった際はトークン文字列と種別を確定させて脱出
				token.setKind(kind);
				token.setStr(tokenStr);
				break;
			// 例外処理: 文字トークン以外であった場合
			default:
				// NullTokenを返す
				reportError("Unexpected Letter");
				return new Token(Kind.NULLTOKEN);
				
		}
		// トークン確定
		return token;
	}
	
	Kind findKind(String tokenStr) {
		// デフォルトはOTHER
		Kind kind = Kind.OTHER;
		// keywordTableからキーワードの種類を特定
		for (Keyword keyword : keywordTable) {
			// 見つかった場合
			if (tokenStr.toLowerCase().equals(keyword.str)) {
				kind = keyword.kind;
				return kind;
			}
		}
		// 見つからなかった場合
		return kind;
	}

	/** 
	 * 字句解析用の文字列定義
	 * */
	private void initialize() {
		
		System.out.print("Initializing... ");
		
		for (int i = 0; i < charKind.length; i++) {
			charKind[i] = Kind.OTHER;
		}
		
		Arrays.setAll(keywordTable, i -> new Keyword());

		charKind['a'] = Kind.LETTER;
		charKind['A'] = Kind.LETTER;
		charKind['c'] = Kind.LETTER;
		charKind['C'] = Kind.LETTER;
		charKind['d'] = Kind.LETTER;
		charKind['D'] = Kind.LETTER;
		charKind['e'] = Kind.LETTER;
		charKind['E'] = Kind.LETTER;
		charKind['n'] = Kind.LETTER;
		charKind['N'] = Kind.LETTER;
		charKind['o'] = Kind.LETTER;
		charKind['O'] = Kind.LETTER;
		charKind['p'] = Kind.LETTER;
		charKind['P'] = Kind.LETTER;
		charKind['r'] = Kind.LETTER;
		charKind['R'] = Kind.LETTER;
		charKind['s'] = Kind.LETTER;
		charKind['S'] = Kind.LETTER;
		charKind['t'] = Kind.LETTER;
		charKind['T'] = Kind.LETTER;
		charKind['u'] = Kind.LETTER;
		charKind['U'] = Kind.LETTER;
		charKind['y'] = Kind.LETTER;
		charKind['Y'] = Kind.LETTER;
		
		keywordTable[0].str = "copy";
		keywordTable[0].kind = Kind.COPY;
		keywordTable[1].str = "paste";
		keywordTable[1].kind = Kind.PASTE;
		keywordTable[2].str = "cut";
		keywordTable[2].kind = Kind.CUT;
		keywordTable[3].str = "redo";
		keywordTable[3].kind = Kind.REDO;
		keywordTable[4].str = "undo";
		keywordTable[4].kind = Kind.UNDO;

		System.out.println("Done");
	}
	
	
	/**
	 * エラーリポート
	 * @param message : エラーメッセージ内容
	 * フォーマット: "[Lex Error]" + message
	 */
	private void reportError(String message) {
		throw new LexicalAnalysisException("[Lex Error]" + message);
	}

}
