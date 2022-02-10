package me.dio.banco.ports;

import me.dio.banco.dominio.Banco;
import me.dio.banco.dominio.Cliente;
import me.dio.banco.dominio.Conta;
import me.dio.banco.dominio.ContaCorrente;

public interface ContaCorrenteRespository {

	void cadastrarConta(Banco banco, Cliente cliente);

	ContaCorrente cadastrarConta(Banco banco, String cpf);

	void extrato(Conta conta);

}
