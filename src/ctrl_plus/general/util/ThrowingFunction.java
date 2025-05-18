package ctrl_plus.general.util;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T, R> {

	/**
	 * 例外を投げられるapply
	 * @throws Exception <br>
	 */
	R apply(T t) throws Exception;	// 例外を投げられるようにすることでthrows句付きの処理内でもラムダ記述可能

	/**
	 * Consumerクラスを用いたラムダ式を使いつつ例外処理をラムダ式外に投げられるメソッド
	 * @param <T>
	 * @param throwingFunction
	 * @return Consumer
	 * <p>
	 * ファイル管理などの例外処理が必要な場面でFunctionのラムダ式を使うときに使用できるメソッド<br>
	 * 入力を出力に変換する機能を提供する<br>
	 * 次のような例で利用できる:<br>
	 * <code>Function&lt;String, String&gt; readFile = ThrowingFunction.rethrow(path -> {<br>
	 * return Files.readString(Path.of(path)); });</code><br>
	 * // IOExceptionが出ても問題ない<br>
	 * <code>String content = readFile.apply("example.txt");</code>
	 * </p>
	 */
	static <T, R> Function<T, R> rethrow(ThrowingFunction<T, R> throwingFunction) {
		return t -> {
			try {
				return throwingFunction.apply(t);	// 例外発生の可能性がある処理
			} catch (Exception e) {
				throw new RuntimeException(e);	// RuntimeExceptionに包む
			}
		};
	}
}
