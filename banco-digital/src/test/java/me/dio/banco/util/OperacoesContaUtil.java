package me.dio.banco.util;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;
import me.dio.banco.dominio.ContaCorrente;
import me.dio.banco.dominio.ContaPoupanca;
import me.dio.banco.repository.ContaCorrenteRepositoryImpl;
import me.dio.banco.repository.ContaPoupancaRepositoryImpl;

public class OperacoesContaUtil {
	
	OperacoesClientes clienteUtil = new OperacoesClientes();
	
	public ContaCorrente cadastrarContaCorrente(Banco banco, String cpf) throws ClienteException {

		ContaCorrenteRepositoryImpl conta = new ContaCorrenteRepositoryImpl();
		return conta.cadastrarConta(banco, cpf);
	}
	
	public ContaPoupanca cadastrarContaPoupanca(Banco banco, String cpf) throws ClienteException {

		ContaPoupancaRepositoryImpl conta = new ContaPoupancaRepositoryImpl();
		return conta.cadastrarConta(banco, cpf);
	}
	
	public void gerarMassaDadosConta(Banco banco) throws ClienteException {
		clienteUtil.cadastrarMassaDados(banco);
		for(Cliente cliente : banco.getClientes()) {
			ContaCorrenteRepositoryImpl contaCorrente = new ContaCorrenteRepositoryImpl();
			contaCorrente.cadastrarConta(banco, cliente.getCpf());
			ContaPoupancaRepositoryImpl contaPoupanca = new ContaPoupancaRepositoryImpl();
			contaPoupanca.cadastrarConta(banco, cliente.getCpf());
		}
	}

}
