package me.dio.banco.ports;

import me.dio.banco.dominio.Banco;
import me.dio.banco.util.ClienteException;
import me.dio.banco.util.TipoOperacaoConta;

public interface OperacoesBancariasRepository {
	
	void sacar(TipoOperacaoConta tipo, Banco banco, Integer numeroConta, Double valor) throws ClienteException;
	void depositar(TipoOperacaoConta tipo, Banco banco, Integer numeroConta, Double valor) throws ClienteException;
	void transferir(TipoOperacaoConta tipo, Banco banco, Integer numeroContaOrigem, Integer numeroContaDestino,
			Double valor) throws ClienteException;
	void extrato(TipoOperacaoConta tipo, Banco banco, Integer numeroConta) throws ClienteException;

}
