package com.zh.http;

public class NetWorkException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 9087953297976020433L;

	public NetWorkException() {
		super("网络异常");
	}

	public NetWorkException(Throwable e) {
		super("网络异常", e);
	}
}
