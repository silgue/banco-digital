package me.dio.banco.repository;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;
import me.dio.banco.dominio.ContaCorrente;
import me.dio.banco.ports.ClienteRepository;
import me.dio.banco.ports.ContaCorrenteRepository;
import me.dio.banco.util.ClienteException;
import me.dio.banco.util.TipoOperacaoConta;
import me.dio.banco.util.ValidadorUtil;

public class ContaCorrenteRepositoryImpl implements ContaCorrenteRepository {

	ValidadorUtil validador = new ValidadorUtil();

	public void cadastrarConta(Banco banco, Cliente cliente) throws ClienteException {
		ClienteRepository clienteRepository = new ClienteRepositoryImpl();
		validador.verificaSePossuiConta(TipoOperacaoConta.CADASTRAR_CONTA_CORRENTE, banco, cliente);
		ContaCorrente conta = new ContaCorrente(banco, clienteRepository.buscaClientePeloCPF(banco, cliente.getCpf()));
		banco.getContasCorrente().add(conta);
	}

	public ContaCorrente cadastrarConta(Banco banco, String cpf) throws ClienteException {
		ClienteRepository clienteRepository = new ClienteRepositoryImpl();
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

	@Override
	public void extrato(ContaCorrente conta) {
		System.out.println("===EXTRATO CONTA===");
		System.out.println("Nome: " + conta.getCliente().getNome());
		System.out.println("CPF: " + conta.getCliente().getCpf());
		System.out.println("Agência: " + conta.getAgencia());
		System.out.println("Número da conta: " + conta.getNumero());
		System.out.println("Saldo da Conta: " + conta.getSaldo());
		System.out.println("Limite Cartão de Crédito: R$ " + conta.getCartao().getLimite());
		System.out.println("===FIM EXTRATO===");
	}
}
