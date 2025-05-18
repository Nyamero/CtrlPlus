package ctrl_plus.general.util;

import java.util.function.Supplier;

@FunctionalInterface
public interface ThrowingSupplier<T> {

	/**
	 * 例外を投げられるget
	 * @throws Exception <br>
	 */
	T get() throws Exception;	// 例外を投げられるようにすることでthrows句付きの処理内でもラムダ記述可能

	/**
	 * Consumerクラスを用いたラムダ式を使いつつ例外処理をラムダ式外に投げられるメソッド
	 * @param <T>
	 * @param throwingSupplier
	 * @return Consumer
	 * <p>
	 * ファイル管理などの例外処理が必要な場面でSupplierのラムダ式を使うときに使用できるメソッド<br>
	 * 値を生成して返す機能を提供する<br>
	 * 次のような例で利用できる:<br>
	 * <code>Supplier&lt;String&gt; supplier = ThrowingSupplier.unchecked(() -> {<br>
	 * 	 if (new File("test.txt").exists()) { return "File Exists" } <br>
	 * 	 else { throw new IOException("File not found"); }});</code>
	 * </p>
	 */
	static <T> Supplier<T> rethrow(ThrowingSupplier<T> throwingSupplier) {
		return () -> {
			try {
				return throwingSupplier.get();	// 例外発生の可能性がある処理
			} catch (Exception e) {
				throw new RuntimeException(e);	// RuntimeExceptionに包む
			}
		};
	}
}
