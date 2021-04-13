package com.bet.me.exception;

public class BetMeApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public BetMeApiException(final String message, final Throwable cause){
		super(message, cause);
	}

	public BetMeApiException(final String message){
		super(message);
	}
}
