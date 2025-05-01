package ctrl_plus.compiler.core;

import java.util.Arrays;

import ctrl_plus.general.enums.EnumKind;
import ctrl_plus.general.exception.LexicalAnalysisException;
import ctrl_plus.general.manager.InputManager;

public class Lexer {

	// プライベート フィールド
	private EnumKind[] charKind = new EnumKind[128];
	
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
		EnumKind kind = EnumKind.NULLTOKEN;
		
		// 空白の読み飛ばし
		while (Character.isWhitespace(currentChar)) {
			currentChar = InputManager.getInstance().getNextChar();
		}

		// EOFチェック
		if (currentChar == '\0') {
			return new Token(EnumKind.EOFTOKEN, "EOF");
		}
		
		// トークンの切り出し
		switch (charKind[currentChar]) {
			// 文字トークン(これのみ受理)
			case LETTER:
				// 文字トークンが続くまで継続
				while(charKind[currentChar] == EnumKind.LETTER && currentChar != '\0') {
					tokenStr += currentChar;
					// System.out.println("[DEBUG]" + tokenStr);
					
					// 空白なしトークン分割用のトークン種類判別
					kind = findKind(tokenStr);
					// トークン分割できる場合は脱出
					if (!kind.equals(EnumKind.NULLTOKEN) && !kind.equals(EnumKind.OTHER) && !kind.equals(EnumKind.EOFTOKEN)) {
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
				return new Token(EnumKind.NULLTOKEN);
				
		}
		// トークン確定
		return token;
	}
	
	EnumKind findKind(String tokenStr) {
		// デフォルトはOTHER
		EnumKind kind = EnumKind.OTHER;
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
			charKind[i] = EnumKind.OTHER;
		}
		
		Arrays.setAll(keywordTable, i -> new Keyword());

		charKind['a'] = EnumKind.LETTER;
		charKind['A'] = EnumKind.LETTER;
		charKind['c'] = EnumKind.LETTER;
		charKind['C'] = EnumKind.LETTER;
		charKind['d'] = EnumKind.LETTER;
		charKind['D'] = EnumKind.LETTER;
		charKind['e'] = EnumKind.LETTER;
		charKind['E'] = EnumKind.LETTER;
		charKind['n'] = EnumKind.LETTER;
		charKind['N'] = EnumKind.LETTER;
		charKind['o'] = EnumKind.LETTER;
		charKind['O'] = EnumKind.LETTER;
		charKind['p'] = EnumKind.LETTER;
		charKind['P'] = EnumKind.LETTER;
		charKind['r'] = EnumKind.LETTER;
		charKind['R'] = EnumKind.LETTER;
		charKind['s'] = EnumKind.LETTER;
		charKind['S'] = EnumKind.LETTER;
		charKind['t'] = EnumKind.LETTER;
		charKind['T'] = EnumKind.LETTER;
		charKind['u'] = EnumKind.LETTER;
		charKind['U'] = EnumKind.LETTER;
		charKind['y'] = EnumKind.LETTER;
		charKind['Y'] = EnumKind.LETTER;
		
		keywordTable[0].str = "copy";
		keywordTable[0].kind = EnumKind.COPY;
		keywordTable[1].str = "paste";
		keywordTable[1].kind = EnumKind.PASTE;
		keywordTable[2].str = "cut";
		keywordTable[2].kind = EnumKind.CUT;
		keywordTable[3].str = "redo";
		keywordTable[3].kind = EnumKind.REDO;
		keywordTable[4].str = "undo";
		keywordTable[4].kind = EnumKind.UNDO;

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
