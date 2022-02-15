package me.dio.banco.ports;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.ContaPoupanca;
import me.dio.banco.util.ClienteException;

public interface ContaPoupancaRepository{
	
	ContaPoupanca buscarConta(Banco banco, Integer numeroConta);

	void extrato(ContaPoupanca buscarConta);

	ContaPoupanca cadastrarConta(Banco banco, String cpf) throws ClienteException;
}
