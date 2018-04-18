package com.sinotech.settle.exception;

/**
 * 请求参数异常
 */
public class IllegalParametersException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IllegalParametersException() {
		super("请求参数异常");
	}

	public IllegalParametersException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

	public IllegalParametersException(String messageKey) {
		super(messageKey);
	}

	public IllegalParametersException(Throwable cause) {
		super("请求参数异常", cause);
	}

}
