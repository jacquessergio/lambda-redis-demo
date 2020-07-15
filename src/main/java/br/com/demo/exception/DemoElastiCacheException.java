package br.com.demo.exception;

public class DemoElastiCacheException extends Exception {

	private static final long serialVersionUID = 1L;

	public DemoElastiCacheException(final String msg) {
		super(msg);
	}

	public DemoElastiCacheException(final String msg, final Throwable th) {
		super(msg, th);
	}
}
