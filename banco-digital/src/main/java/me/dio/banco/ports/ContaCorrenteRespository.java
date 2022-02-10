package me.dio.banco.ports;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;
import me.dio.banco.dominio.ContaCorrente;
import me.dio.banco.util.ClienteException;

public interface ContaCorrenteRespository {

	void cadastrarConta(Banco banco, Cliente cliente) throws ClienteException;

	ContaCorrente cadastrarConta(Banco banco, String cpf) throws ClienteException;

	void extrato(ContaCorrente conta);

}
