package ctrl_plus.general.util;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T> {

	/**
	 * 例外を投げられるaccept
	 * @param t
	 * @throws Exception <br>
	 */
	void accept(T t) throws Exception;	// 例外を投げられるようにすることでthrows句付きの処理内でもラムダ記述可能

	/**
	 * Consumerクラスを用いたラムダ式を使いつつ例外処理をラムダ式外に投げられるメソッド
	 * @param <T>
	 * @param throwingConsumer
	 * @return Consumer
	 * <p>
	 * ファイル管理などの例外処理が必要な場面でConsumerのラムダ式を使うときに使用できるメソッド<br>
	 * 次のような例で利用できる:<br>
	 * <code>exList.forEach(ThrowingConsumer.rethrow(index -> System.out.println(index)));</code>
	 * </p>
	 */
	static <T> Consumer<T> rethrow(ThrowingConsumer<T> throwingConsumer) {
		return t -> {
			try {
				throwingConsumer.accept(t);	// 例外発生の可能性がある処理
			} catch (Exception e) {
				throw new RuntimeException(e);	// RuntimeExceptionに包む
			}
		};
	}
}
