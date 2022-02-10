package me.dio.banco.repository;

import java.util.Scanner;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;
import me.dio.banco.dominio.ContaCorrente;
import me.dio.banco.ports.ClienteRepository;
import me.dio.banco.util.ClienteException;
import me.dio.banco.util.ValidadorUtil;

public class ClienteRepositoryImpl implements ClienteRepository {

	public Cliente cadastrarCliente(Banco banco, String cpf) {
		ValidadorUtil validador = new ValidadorUtil();

		Scanner scan = new Scanner(System.in);

		Integer idade = 0;
		Double remuneracaoMensal = 0.0;

		System.out.println("Informe o nome do cliente:\n");
		String nome = scan.nextLine();

		idade = (Integer) validador.validaFormato("Informe a idade do cliente:\n", idade, scan);

		remuneracaoMensal = (Double) validador.validaFormato("Informe a remuneração mensal do cliente:\n",
				remuneracaoMensal, scan);

		Cliente cliente = new Cliente(nome, cpf, idade, remuneracaoMensal);
		banco.getClientes().add(cliente);
		return cliente;
	}

	public Cliente buscaClientePeloCPF(Banco banco, String cpf) throws ClienteException {

		for (ContaCorrente conta : banco.getContasCorrente()) {
			if (conta.getCliente().getCpf().equals(cpf)) {
				throw new ClienteException();
			}
		}

		for (Cliente cliente : banco.getClientes()) {
			if (cliente.getCpf().equals(cpf)) {
				return cliente;
			}
		}

		return cadastrarCliente(banco, cpf);
	}

}
