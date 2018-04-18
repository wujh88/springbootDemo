package com.sinotech.settle.exception;

/**
 * 系统IO异常
 */
public class CommonIOException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CommonIOException() {
		super("系统IO异常");
	}

	public CommonIOException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

	public CommonIOException(String messageKey) {
		super(messageKey);
	}

	public CommonIOException(Throwable cause) {
		super("系统IO异常", cause);
	}

}
