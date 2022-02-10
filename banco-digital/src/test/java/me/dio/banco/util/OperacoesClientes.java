package me.dio.banco.util;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;

public class OperacoesClientes {
	
	public void cadastrarCliente(Banco banco, String nome, String cpf, Integer idade, Double remuneracaoMensal)
			throws ClienteException {
		
		Cliente cliente = new Cliente(nome, cpf, idade, remuneracaoMensal);
		banco.getClientes().add(cliente);
	}

}
