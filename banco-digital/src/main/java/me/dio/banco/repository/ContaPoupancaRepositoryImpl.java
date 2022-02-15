package me.dio.banco.repository;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;
import me.dio.banco.dominio.ContaPoupanca;
import me.dio.banco.ports.ClienteRepository;
import me.dio.banco.ports.ContaPoupancaRepository;
import me.dio.banco.util.ClienteException;
import me.dio.banco.util.TipoOperacaoConta;
import me.dio.banco.util.ValidadorUtil;

public class ContaPoupancaRepositoryImpl implements ContaPoupancaRepository{
	
	ValidadorUtil validador = new ValidadorUtil();

	public void extrato(ContaPoupanca conta) {
		System.out.println("===EXTRATO CONTA===");
		System.out.println("Nome: " + conta.getCliente().getNome());
		System.out.println("CPF: " + conta.getCliente().getCpf());
		System.out.println("Agência: " + conta.getAgencia());
		System.out.println("Número da conta: " + conta.getNumero());
		System.out.println("Saldo da Conta: " + conta.getSaldo());
		System.out.println("===FIM EXTRATO===");
	}

	public void cadastrarConta(Banco banco, Cliente cliente) throws ClienteException {
		ClienteRepository clienteRepository = new ClienteRepositoryImpl();
		validador.verificaSePossuiConta(TipoOperacaoConta.CADASTRAR_CONTA_POUPANCA, banco, cliente);
		ContaPoupanca conta = new ContaPoupanca(banco, clienteRepository.buscaClientePeloCPF(banco, cliente.getCpf()));
		banco.getContasPoupanca().add(conta);
	}

	public ContaPoupanca cadastrarConta(Banco banco, String cpf) throws ClienteException {
		ClienteRepository clienteRepository = new ClienteRepositoryImpl();
		validador.verificaSePossuiConta(TipoOperacaoConta.CADASTRAR_CONTA_POUPANCA, banco, clienteRepository.buscaClientePeloCPF(banco, cpf));
		ContaPoupanca conta = new ContaPoupanca(banco, clienteRepository.buscaClientePeloCPF(banco, cpf));
		banco.getContasPoupanca().add(conta);
		return conta;
	}

	public ContaPoupanca buscarConta(Banco banco, Integer numeroConta) {
		
		for(ContaPoupanca conta : banco.getContasPoupanca()) {
			if(conta.getNumero() == numeroConta){
				return conta;
			}
		}
		
		return null;
	}
}
