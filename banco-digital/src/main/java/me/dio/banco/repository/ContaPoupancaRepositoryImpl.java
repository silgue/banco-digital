package me.dio.banco.repository;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;
import me.dio.banco.dominio.ContaPoupanca;
import me.dio.banco.ports.ContaPoupancaRepository;
import me.dio.banco.util.ClienteException;
import me.dio.banco.util.TipoOperacaoConta;
import me.dio.banco.util.ValidadorUtil;

public class ContaPoupancaRepositoryImpl implements ContaPoupancaRepository{
	
	ContaRepositoryImpl contaRepository = new ContaRepositoryImpl();
	
	ValidadorUtil validador = new ValidadorUtil();

	public void extrato(ContaPoupanca conta) {
		contaRepository.extrato(conta);
	}

	public void cadastrarConta(Banco banco, Cliente cliente) throws ClienteException {
		ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
		validador.verificaSePossuiConta(TipoOperacaoConta.CADASTRAR_CONTA_POUPANCA, banco, cliente);
		ContaPoupanca conta = new ContaPoupanca(banco, clienteRepository.buscaClientePeloCPF(banco, cliente.getCpf()));
		banco.getContasPoupanca().add(conta);
	}

	public ContaPoupanca cadastrarConta(Banco banco, String cpf) throws ClienteException {
		ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
		validador.verificaSePossuiConta(TipoOperacaoConta.CADASTRAR_CONTA_POUPANCA, banco, clienteRepository.buscaClientePeloCPF(banco, cpf));
		ContaPoupanca conta = new ContaPoupanca(banco, clienteRepository.buscaClientePeloCPF(banco, cpf));
		banco.getContasPoupanca().add(conta);
		return conta;
	}
}
