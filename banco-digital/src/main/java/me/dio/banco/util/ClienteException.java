package me.dio.banco.util;

import java.beans.ExceptionListener;

public class ClienteException extends Throwable implements ExceptionListener{
	
	public void exceptionThrown(Exception e) {
		System.out.println("Cliente j� possui uma conta corrente");
		
	}

}
