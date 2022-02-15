package me.dio.banco.ports;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.ContaCorrente;
import me.dio.banco.util.ClienteException;

public interface ContaCorrenteRepository{
	void extrato(ContaCorrente conta);
	ContaCorrente buscarConta(Banco banco, Integer numeroConta) throws ClienteException;
	ContaCorrente cadastrarConta(Banco banco, String cpf) throws ClienteException; 

}
