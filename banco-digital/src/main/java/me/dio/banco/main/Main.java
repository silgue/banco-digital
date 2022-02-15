package me.dio.banco.main;

import java.util.Scanner;

import me.dio.banco.dominio.Banco;
import me.dio.banco.ports.OperacoesBancariasRepository;
import me.dio.banco.repository.ContaCorrenteRepositoryImpl;
import me.dio.banco.repository.ContaPoupancaRepositoryImpl;
import me.dio.banco.repository.OperacoesBancariasRepositoryImpl;
import me.dio.banco.util.ClienteException;
import me.dio.banco.util.TipoOperacaoConta;
import me.dio.banco.util.ValidadorUtil;

public class Main {

	public static void main(String[] args) {

		Banco banco = new Banco();

		ValidadorUtil validador = new ValidadorUtil();

		OperacoesBancariasRepository operacoesBancarias = new OperacoesBancariasRepositoryImpl();

		Scanner scan = new Scanner(System.in);

		boolean menu = true;
		String cpf = "";
		Integer numeroContaOrigem = 0;
		Integer numeroContaDestino = 0;
		Double valor = 0.0;

		while (menu) {
			System.out.println("Pressione Enter para continuar...");
			scan.nextLine();
			Integer opcao = 0;
			opcao = (Integer) validador.validaFormato(
					"Informe a opção desejada: \n" + "1 - Criar conta corrente\n" + "2 - Criar conta Poupança\n"
							+ "3 - Sacar da conta corrente\n" + "4 - Depositar na conta corrente\n"
							+ "5 - Sacar da conta poupança. \n" + "6 - Depositar na conta poupança. \n"
							+ "7 - Transferência entre contas correntes. \n"
							+ "8 - Transferir da conta corrente para conta poupança. \n"
							+ "9 - Transferência entre contas poupança. \n"
							+ "10 - Transferir da conta poupança para uma conta concorrente. \n" 
							+ "11 - Extrato Conta Corrente. \n" + "12 - Extrato Conta Poupança. \n" + "13 - Sair. \n",
					opcao, scan);
			switch (opcao) {
			case 1:
				System.out.println("Informe o CPF do Cliente: \n");
				cpf = scan.nextLine();
				try {
					ContaCorrenteRepositoryImpl contaCorrente = new ContaCorrenteRepositoryImpl();
					contaCorrente.extrato(contaCorrente.cadastrarConta(banco, cpf));
				} catch (ClienteException c) {
					System.out.println("Cliente já possui conta corrente. \n");
				}

				break;
			case 2:
				System.out.println("Informe o CPF do Cliente: \n");
				cpf = scan.nextLine();
				try {
					ContaPoupancaRepositoryImpl contaPoupanca = new ContaPoupancaRepositoryImpl();
					contaPoupanca.extrato(contaPoupanca.cadastrarConta(banco, cpf));
				} catch (ClienteException c) {
					c.getMessage();
				}
				break;
			case 3:
				System.out.println("Informe o número da conta: \n");
				numeroContaDestino = Integer.parseInt(scan.nextLine());
				System.out.println("Informe o valor: \n");
				valor = Double.parseDouble(scan.nextLine());
				try {
					operacoesBancarias.sacar(TipoOperacaoConta.SACAR_CONTA_CORRENTE, banco, numeroContaDestino, valor);
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_CORRENTE, banco, numeroContaDestino);
				} catch (ClienteException c) {
					c.getMessage();
				}
				break;
			case 4:
				System.out.println("Informe o número da conta: \n");
				numeroContaDestino = Integer.parseInt(scan.nextLine());
				System.out.println("Informe o valor: \n");
				valor = Double.parseDouble(scan.nextLine());
				try {
					operacoesBancarias.depositar(TipoOperacaoConta.DEPOSITAR_CONTA_CORRENTE, banco, numeroContaDestino,
							valor);
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_CORRENTE, banco, numeroContaDestino);
				} catch (ClienteException c) {
					c.getMessage();
				}
				break;
			case 5:
				System.out.println("Informe o número da conta: \n");
				numeroContaDestino = Integer.parseInt(scan.nextLine());
				System.out.println("Informe o valor: \n");
				valor = Double.parseDouble(scan.nextLine());
				try {
					operacoesBancarias.sacar(TipoOperacaoConta.SACAR_CONTA_POUPANÇA, banco, numeroContaDestino, valor);
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_POUPANCA, banco, numeroContaDestino);
				} catch (ClienteException c) {
					c.getMessage();
				}
				break;
			case 6:
				System.out.println("Informe o número da conta: \n");
				numeroContaDestino = Integer.parseInt(scan.nextLine());
				System.out.println("Informe o valor: \n");
				valor = Double.parseDouble(scan.nextLine());
				try {
					operacoesBancarias.depositar(TipoOperacaoConta.DEPOSITAR_CONTA_POUPANCA, banco, numeroContaDestino,
							valor);
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_POUPANCA, banco, numeroContaDestino);
				} catch (ClienteException c) {
					c.getMessage();
				}
				break;
			case 7:
				System.out.println("Informe o número da conta de Origem: \n");
				numeroContaOrigem = Integer.parseInt(scan.nextLine());
				System.out.println("Informe o número da conta de Destino: \n");
				numeroContaDestino = Integer.parseInt(scan.nextLine());
				System.out.println("Informe o valor: \n");
				valor = Double.parseDouble(scan.nextLine());
				try {
					operacoesBancarias.transferir(TipoOperacaoConta.TRANSFERIR_CONTA_CORRENTE_PARA_CORRENTE, banco,
							numeroContaOrigem, numeroContaDestino, valor);
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_CORRENTE, banco, numeroContaOrigem);
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_CORRENTE, banco, numeroContaDestino);
				} catch (ClienteException c) {
					c.getMessage();
				}
				break;
			case 8:
				System.out.println("Informe o número da conta de Origem: \n");
				numeroContaOrigem = Integer.parseInt(scan.nextLine());
				System.out.println("Informe o número da conta de Destino: \n");
				numeroContaDestino = Integer.parseInt(scan.nextLine());
				System.out.println("Informe o valor: \n");
				valor = Double.parseDouble(scan.nextLine());
				try {
					operacoesBancarias.transferir(TipoOperacaoConta.TRANSFERIR_CONTA_CORRENTE_PARA_POUPANCA, banco,
							numeroContaOrigem, numeroContaDestino, valor);
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_CORRENTE, banco, numeroContaOrigem);
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_POUPANCA, banco, numeroContaDestino);
				} catch (ClienteException c) {
					c.getMessage();
				}
				break;
			case 9:
				System.out.println("Informe o número da conta de Origem: \n");
				numeroContaOrigem = Integer.parseInt(scan.nextLine());
				System.out.println("Informe o número da conta de Destino: \n");
				numeroContaDestino = Integer.parseInt(scan.nextLine());
				System.out.println("Informe o valor: \n");
				valor = Double.parseDouble(scan.nextLine());
				try {
					operacoesBancarias.transferir(TipoOperacaoConta.TRANSFERIR_CONTA_POUPANCA_PARA_POUPANCA, banco,
							numeroContaOrigem, numeroContaDestino, valor);
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_POUPANCA, banco, numeroContaOrigem);
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_POUPANCA, banco, numeroContaDestino);
				} catch (ClienteException c) {
					c.getMessage();
				}
				break;
			case 10:
				System.out.println("Informe o número da conta de Origem: \n");
				numeroContaOrigem = Integer.parseInt(scan.nextLine());
				System.out.println("Informe o número da conta de Destino: \n");
				numeroContaDestino = Integer.parseInt(scan.nextLine());
				System.out.println("Informe o valor: \n");
				valor = Double.parseDouble(scan.nextLine());
				try {
					operacoesBancarias.transferir(TipoOperacaoConta.TRANSFERIR_CONTA_POUPANCA_PARA_CORRENTE, banco,
							numeroContaOrigem, numeroContaDestino, valor);
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_POUPANCA, banco, numeroContaOrigem);
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_CORRENTE, banco, numeroContaDestino);
				} catch (ClienteException c) {
					c.getMessage();
				}
				break;
			case 11:
				System.out.println("Informe o número da conta Corrente: \n");
				numeroContaDestino = Integer.parseInt(scan.nextLine());
				try {
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_CORRENTE, banco, numeroContaDestino);
				} catch (ClienteException c) {
					c.getMessage();
				}
				
				break;
			case 12:
				System.out.println("Informe o número da conta Poupança: \n");
				numeroContaDestino = Integer.parseInt(scan.nextLine());
				try {
					operacoesBancarias.extrato(TipoOperacaoConta.EXTRATO_CONTA_POUPANCA, banco, numeroContaDestino);
				} catch (ClienteException c) {
					c.getMessage();
				}
				
				break;
			case 13:
				menu = false;
				scan.close();
				break;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}

}
