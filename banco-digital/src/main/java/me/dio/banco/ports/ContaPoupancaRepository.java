package me.dio.banco.ports;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;
import me.dio.banco.dominio.Conta;
import me.dio.banco.dominio.ContaCorrente;
import me.dio.banco.dominio.ContaPoupanca;
import me.dio.banco.util.ClienteException;

public interface ContaPoupancaRepository {
	
	void cadastrarConta(Banco banco, Cliente cliente) throws ClienteException;

	ContaPoupanca cadastrarConta(Banco banco, String cpf) throws ClienteException;

	void extrato(ContaPoupanca conta);

}
