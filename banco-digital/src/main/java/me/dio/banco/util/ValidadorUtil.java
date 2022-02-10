package me.dio.banco.util;

import java.util.Scanner;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;
import me.dio.banco.dominio.ContaCorrente;
import me.dio.banco.dominio.ContaPoupanca;

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
					System.out.println("Formato inválido. Informe um número inteiro.");
				}
			}
			if (o instanceof Double) {
				try {
					o = Double.parseDouble(scan.nextLine());
					continua = false;
				} catch (NumberFormatException e) {
					System.out.println("Formato inválido. Informe um número no formato 10000.00");
				}
			}
		}

		return o;
	}
	
	public void verificaSePossuiConta(TipoOperacaoConta tipo ,Banco banco, Cliente cliente) throws ClienteException {
		if(tipo == TipoOperacaoConta.CADASTRAR_CONTA_CORRENTE) {
			for (ContaCorrente conta : banco.getContasCorrente()) {
				if (conta.getCliente().getCpf().equals(cliente.getCpf())) {
					throw new ClienteException("O cliente já possui conta corrente.\n");
				}
			}
		}else {
			for (ContaPoupanca conta : banco.getContasPoupanca()) {
				if (conta.getCliente().getCpf().equals(cliente.getCpf())) {
					throw new ClienteException("O cliente já possui conta corrente.\n");
				}
			}
		}
		
	}
}
