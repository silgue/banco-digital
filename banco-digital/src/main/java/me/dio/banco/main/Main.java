package me.dio.banco.main;

import java.util.Scanner;

import me.dio.banco.dominio.Banco;
import me.dio.banco.repository.ContaCorrenteRepositoryImpl;
import me.dio.banco.repository.ContaPoupancaRepositoryImpl;
import me.dio.banco.util.ClienteException;
import me.dio.banco.util.ValidadorUtil;

public class Main {

	public static void main(String[] args) {

		Banco banco = new Banco();

		ValidadorUtil validador = new ValidadorUtil();

		Scanner scan = new Scanner(System.in);

		boolean menu = true;
		String cpf = "";
		
		while (menu) {
			System.out.println("Pressione Enter para continuar...");
			scan.nextLine();
			Integer opcao = 0;
			opcao = (Integer) validador
					.validaFormato(
							"Informe a opção desejada: \n" + "1 - Criar conta corrente\n" + "2 - Criar conta Poupança\n"
									+ "3 - Sacar\n" + "4 - Depositar\n" + "5 - Transferir\n" + "6 - Sair\n",
							opcao, scan);
			switch (opcao) {
			case 1:
				System.out.println("Informe o CPF do Cliente: \n");
				cpf = scan.nextLine();
				try {
					ContaCorrenteRepositoryImpl conta = new ContaCorrenteRepositoryImpl();
					conta.extrato(conta.cadastrarConta(banco, cpf));
				} catch (ClienteException c) {
					System.out.println("Cliente já possui conta corrente. \n");
				}

				break;
			case 2:
				System.out.println("Informe o CPF do Cliente: \n");
				cpf = scan.nextLine();
				try {
					ContaPoupancaRepositoryImpl conta = new ContaPoupancaRepositoryImpl();
					conta.extrato(conta.cadastrarConta(banco, cpf));
				} catch (ClienteException c) {
					System.out.println("Cliente já possui conta poupança.\n");
				}
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				menu = false;
				scan.close();
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}

}
