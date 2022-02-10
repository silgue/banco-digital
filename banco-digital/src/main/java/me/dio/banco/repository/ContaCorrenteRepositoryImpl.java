package me.dio.banco.repository;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;
import me.dio.banco.dominio.Conta;
import me.dio.banco.dominio.ContaCorrente;
import me.dio.banco.ports.ContaCorrenteRespository;
import me.dio.banco.util.ClienteException;

public class ContaCorrenteRepositoryImpl implements ContaCorrenteRespository {

	public void extrato(Conta conta) {

		System.out.println("Agência: " + conta.getAgencia());
		System.out.println("Número da conta: " + conta.getNumero());
		System.out.println("Saldo da Conta: " + conta.getSaldo());
		System.out.println("Limite Cartão de Crédito: R$ " + conta.getCartao().getLimite());
		System.out.println("Nome: " + conta.getCliente().getNome());
		System.out.println("CPF: " + conta.getCliente().getCpf() + "\n");

	}

	public void cadastrarConta(Banco banco, Cliente cliente) throws ClienteException {
		ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
		ContaCorrente conta = new ContaCorrente(clienteRepository.buscaClientePeloCPF(banco, cliente.getCpf()));
		banco.getContasCorrente().add(conta);
	}

	public ContaCorrente cadastrarConta(Banco banco, String cpf) throws ClienteException {
		ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
		ContaCorrente conta = new ContaCorrente(clienteRepository.buscaClientePeloCPF(banco, cpf));
		banco.getContasCorrente().add(conta);
		return conta;

	}
}
