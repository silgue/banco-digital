package me.dio.banco.util;

import java.beans.ExceptionListener;

public class ClienteException extends Throwable implements ExceptionListener{
	
	public ClienteException(String mensagem) {
		System.out.println(mensagem);
	}

	public void exceptionThrown(Exception e) {
		System.out.println("Cliente já possui uma conta desse tipo.");
		
	}

}
