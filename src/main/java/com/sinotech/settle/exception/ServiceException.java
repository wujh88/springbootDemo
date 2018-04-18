package com.sinotech.settle.exception;

/**
 * 业务逻辑操作异常
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

	public ServiceException(String messageKey) {
		super(messageKey);
	}

	public ServiceException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

}
