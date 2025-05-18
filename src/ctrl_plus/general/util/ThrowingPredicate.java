package ctrl_plus.general.util;

import java.util.function.Predicate;

@FunctionalInterface
public interface ThrowingPredicate<T> {

	/**
	 * 例外を投げられるtest
	 * @param t
	 * @throws Exception <br>
	 */
	boolean test(T t) throws Exception;	// 例外を投げられるようにすることでthrows句付きの処理内でもラムダ記述可能

	/**
	 * Consumerクラスを用いたラムダ式を使いつつ例外処理をラムダ式外に投げられるメソッド
	 * @param <T>
	 * @param throwingConsumer
	 * @return Consumer
	 * <p>
	 * ファイル管理などの例外処理が必要な場面でPredicateのラムダ式を使うときに使用できるメソッド<br>
	 * 真偽値を返す条件判定を提供する<br>
	 * 次のような例で利用できる:<br>
	 * <code>Predicate&lt;String&gt; isReadable = ThrowingPredicate.rethrow(path -> {<br>
	 * return Files.isReadable(Path.of(path));});
	 * <br>
	 * if (isReadable.test("example.txt") { 
	 * 	System.out.println("readable"); 
	 * }</code>
	 * </p>
	 */
	static <T> Predicate<T> rethrow(ThrowingPredicate<T> throwingPredicate) {
		return t -> {
			try {
				return throwingPredicate.test(t);	// 例外発生の可能性がある処理
			} catch (Exception e) {
				throw new RuntimeException(e);	// RuntimeExceptionに包む
			}
		};
	}
}
