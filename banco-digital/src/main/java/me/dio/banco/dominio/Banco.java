package me.dio.banco.dominio;

import java.util.ArrayList;
import java.util.List;

public class Banco {

	public List<Cliente> clientes;
	public List<ContaCorrente> contasCorrente;
	public List<ContaPoupanca> contasPoupanca;

	public Banco() {
		this.clientes = new ArrayList<Cliente>();
		this.contasCorrente = new ArrayList<ContaCorrente>();
		this.contasPoupanca = new ArrayList<ContaPoupanca>();
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<ContaCorrente> getContasCorrente() {
		return contasCorrente;
	}
	
	public List<ContaPoupanca> getContasPoupanca() {
		return contasPoupanca;
	}

}
