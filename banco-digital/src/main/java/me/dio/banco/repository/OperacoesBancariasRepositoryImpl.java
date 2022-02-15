package me.dio.banco.repository;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Conta;
import me.dio.banco.ports.ContaCorrenteRepository;
import me.dio.banco.ports.ContaPoupancaRepository;
import me.dio.banco.ports.OperacoesBancariasRepository;
import me.dio.banco.util.ClienteException;
import me.dio.banco.util.TipoOperacaoConta;

public class OperacoesBancariasRepositoryImpl implements OperacoesBancariasRepository{

	ContaCorrenteRepository contaCorrenteRepositorio = new ContaCorrenteRepositoryImpl();
	ContaPoupancaRepository contaPoupancaRepositorio = new ContaPoupancaRepositoryImpl();

	public void sacar(TipoOperacaoConta tipo, Banco banco, Integer numeroConta, Double valor) throws ClienteException {
		if (tipo == TipoOperacaoConta.SACAR_CONTA_CORRENTE) {
			Conta conta = contaCorrenteRepositorio.buscarConta(banco, numeroConta);
			conta.sacar(conta, valor);
		} else if (tipo == TipoOperacaoConta.SACAR_CONTA_POUPANÇA) {
			Conta conta = contaPoupancaRepositorio.buscarConta(banco, numeroConta);
			conta.sacar(conta, valor);
		}
	}

	public void depositar(TipoOperacaoConta tipo, Banco banco, Integer numeroConta, Double valor)
			throws ClienteException {
		if (tipo == TipoOperacaoConta.DEPOSITAR_CONTA_CORRENTE) {
			Conta conta = contaCorrenteRepositorio.buscarConta(banco, numeroConta);
			conta.depositar(conta, valor);
		} else if (tipo == TipoOperacaoConta.DEPOSITAR_CONTA_POUPANCA) {
			Conta conta = contaPoupancaRepositorio.buscarConta(banco, numeroConta);
			conta.depositar(conta, valor);
		}
	}

	public void transferir(TipoOperacaoConta tipo, Banco banco, Integer numeroContaOrigem, Integer numeroContaDestino,
			Double valor) throws ClienteException {
		if (tipo == TipoOperacaoConta.TRANSFERIR_CONTA_CORRENTE_PARA_CORRENTE) {
			sacar(TipoOperacaoConta.SACAR_CONTA_CORRENTE, banco, numeroContaOrigem, valor);
			depositar(TipoOperacaoConta.DEPOSITAR_CONTA_CORRENTE, banco, numeroContaDestino, valor);
		} else if (tipo == TipoOperacaoConta.TRANSFERIR_CONTA_CORRENTE_PARA_POUPANCA) {
			sacar(TipoOperacaoConta.SACAR_CONTA_CORRENTE, banco, numeroContaOrigem, valor);
			depositar(TipoOperacaoConta.DEPOSITAR_CONTA_POUPANCA, banco, numeroContaDestino, valor);
		} else if (tipo == TipoOperacaoConta.TRANSFERIR_CONTA_POUPANCA_PARA_CORRENTE) {
			sacar(TipoOperacaoConta.SACAR_CONTA_POUPANÇA, banco, numeroContaOrigem, valor);
			depositar(TipoOperacaoConta.DEPOSITAR_CONTA_CORRENTE, banco, numeroContaDestino, valor);
		} else if (tipo == TipoOperacaoConta.TRANSFERIR_CONTA_POUPANCA_PARA_POUPANCA) {
			sacar(TipoOperacaoConta.SACAR_CONTA_POUPANÇA, banco, numeroContaOrigem, valor);
			depositar(TipoOperacaoConta.DEPOSITAR_CONTA_POUPANCA, banco, numeroContaDestino, valor);
		}
	}

	public void extrato(TipoOperacaoConta tipo, Banco banco, Integer numeroConta) throws ClienteException {
		if(tipo == TipoOperacaoConta.EXTRATO_CONTA_CORRENTE) {
			contaCorrenteRepositorio.extrato(contaCorrenteRepositorio.buscarConta(banco, numeroConta));
		}else {
			contaPoupancaRepositorio.extrato(contaPoupancaRepositorio.buscarConta(banco, numeroConta));
		}
	}
	
}
