package com.sinotech.settle.exception;

/**
 * 业务禁止异常
 */
public class BusinessProhibitionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BusinessProhibitionException() {
		super("业务禁止异常");
	}

	public BusinessProhibitionException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}

	public BusinessProhibitionException(String messageKey) {
		super(messageKey);
	}

	public BusinessProhibitionException(Throwable cause) {
		super("业务禁止异常", cause);
	}

}
