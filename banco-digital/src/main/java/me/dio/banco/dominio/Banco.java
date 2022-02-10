package me.dio.banco.dominio;

import java.util.ArrayList;
import java.util.List;

public class Banco {

	public List<Cliente> clientes;
	public List<ContaCorrente> contasCorrente;

	public Banco() {
		this.clientes = new ArrayList<Cliente>();
		this.contasCorrente = new ArrayList<ContaCorrente>();
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<ContaCorrente> getContasCorrente() {
		return contasCorrente;
	}

}
