package ctrl_plus.general.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 入力文字列　シングルトンインスタンス
 * */

public class InputManager {

	/************* インスタンス関連 ***********/
	// シングルトンインスタンス
	private static final InputManager INSTANCE = new InputManager();

	// コンストラクタ
	private InputManager() {
		inStr = null;
		index = 0;
		line = 1;
		column = 0;
		getInput();
	}

	// インスタンスのゲッター
	public static InputManager getInstance() {
		return INSTANCE;
	}

	/******** プライベート フィールド *********/
	private String inStr;	// 入力文字列
	private int index;		// 文字数カウント
	private int line;		// 行
	private int column;		// 列

	/************* 通常メソッド ***************/
	public void getInput() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println("入力文字列を待っています...");
		System.out.print("$ ");

		try {
			inStr = br.readLine();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getStr() {
		return inStr;
	}	

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public char getNextChar() {
		try {
			char nextChar = inStr.charAt(index);
			// 空白の読み飛ばし
			index++;
			column++;
			if (nextChar == '\n') {
				column = 0;
				line++;
			}
			return nextChar;
		} catch (StringIndexOutOfBoundsException e) {
			return '\0';
		}
	}
	

}
