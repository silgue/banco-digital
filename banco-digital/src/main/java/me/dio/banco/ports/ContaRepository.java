package me.dio.banco.ports;

import me.dio.banco.dominio.Conta;

public interface ContaRepository {
	
	void extrato(Conta conta);

}
