package me.dio.banco.util;

import java.beans.ExceptionListener;

public class ClienteException extends Throwable implements ExceptionListener{
	
	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public ClienteException(String mensagem) {
		msg = mensagem;
		System.out.println(msg);
	}

	public void exceptionThrown(Exception e) {
		System.out.println("Cliente já possui uma conta desse tipo.");
		
	}

}
