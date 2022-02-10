package me.dio.banco.dominio;

import me.dio.banco.util.ClienteException;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(Banco banco, Cliente cliente) throws ClienteException {
		for (ContaPoupanca conta : banco.getContasPoupanca()) {
			if (conta.getCliente().getCpf().equals(cliente.getCpf())) {
				throw new ClienteException("O cliente j� possui uma conta poupan�a. \n");
			}
		}
		super.cadastrarConta(cliente);
	}
}
