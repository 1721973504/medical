package com.gxuwz.medical.exception;
/**
 * 未设置缴费标准异常
 * @author 演示
 *
 */
public class PayperiodUnfoundException extends Exception {

	public PayperiodUnfoundException() {
		super();
	}

	public PayperiodUnfoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PayperiodUnfoundException(String message) {
		super(message);
	}

}
