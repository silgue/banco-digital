package me.dio.banco.util;

import java.util.Scanner;

public class ValidadorUtil {

	public Object validaFormato(String mensagem, Object o, Scanner scan) {
		boolean continua = true;
		while (continua) {
			System.out.println(mensagem);
			if (o instanceof Integer) {
				try {
					o = Integer.parseInt(scan.nextLine());
					continua = false;
				} catch (NumberFormatException e) {
					System.out.println("Formato inv�lido. Informe um n�mero inteiro.");
				}
			}
			if (o instanceof Double) {
				try {
					o = Double.parseDouble(scan.nextLine());
					continua = false;
				} catch (NumberFormatException e) {
					System.out.println("Formato inv�lido. Informe um n�mero no formato 10000.00");
				}
			}
		}

		return o;
	}
}
