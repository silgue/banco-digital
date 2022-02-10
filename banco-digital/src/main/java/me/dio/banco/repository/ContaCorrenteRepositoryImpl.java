package me.dio.banco.repository;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;
import me.dio.banco.dominio.ContaCorrente;
import me.dio.banco.ports.ContaCorrenteRespository;
import me.dio.banco.util.ClienteException;
import me.dio.banco.util.TipoOperacaoConta;
import me.dio.banco.util.ValidadorUtil;

public class ContaCorrenteRepositoryImpl implements ContaCorrenteRespository {

	ContaRepositoryImpl contaRepository = new ContaRepositoryImpl();

	ValidadorUtil validador = new ValidadorUtil();

	public void extrato(ContaCorrente conta) {
		contaRepository.extrato(conta);
		System.out.println("Limite Cartão de Crédito: R$ " + conta.getCartao().getLimite());
	}

	public void cadastrarConta(Banco banco, Cliente cliente) throws ClienteException {
		ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
		validador.verificaSePossuiConta(TipoOperacaoConta.CADASTRAR_CONTA_CORRENTE, banco, cliente);
		ContaCorrente conta = new ContaCorrente(banco, clienteRepository.buscaClientePeloCPF(banco, cliente.getCpf()));
		banco.getContasCorrente().add(conta);
	}

	public ContaCorrente cadastrarConta(Banco banco, String cpf) throws ClienteException {
		ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
		validador.verificaSePossuiConta(TipoOperacaoConta.CADASTRAR_CONTA_CORRENTE, banco,
				clienteRepository.buscaClientePeloCPF(banco, cpf));
		ContaCorrente conta = new ContaCorrente(banco, clienteRepository.buscaClientePeloCPF(banco, cpf));
		banco.getContasCorrente().add(conta);
		return conta;

	}

	public ContaCorrente buscarConta(Banco banco, Integer numeroConta) throws ClienteException {
		verificarSeContaExiste(banco, numeroConta);
		for (ContaCorrente conta : banco.getContasCorrente()) {
			if (conta.getNumero() == numeroConta) {
				return conta;
			}
		}
		return null;
	}

	public void verificarSeContaExiste(Banco banco, Integer numeroConta) throws ClienteException {
		boolean contaExiste = false;
		for (ContaCorrente conta : banco.getContasCorrente()) {
			if (conta.getNumero() == numeroConta) {
				contaExiste = true;
			}
		}
		if (!contaExiste)
			throw new ClienteException("Conta não encontrada. \n");
	}
}
